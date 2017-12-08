//用于求解函数y=x^7+x^6-100x^5+200x^4-300x^3-13579x^2-2468x+123456789在（-8，8）之间的最小值
package main

import (
	"math/rand"
	"fmt"
	"time"
	"os"
	"strconv"
)

//染色体数量
const COUNT int = 20

//最大循环次数，若超过该次数程序仍然没有终止则退出程序并未达到最优
const MAXLOOP int = 1500

//两次结果小于该值则认为结果没有改变
const MINIMAL_SUB float32 = 100000

//交叉概率
const CROSS_PROBABILITY float32 = 0.2

//变异概率
const CHANGE_PROBABILITY float32 = 0.01

//最大不变次数
const UNCHANGE_COUNT_MAX int = 100

type chromosome struct {
	//用int的后十四位表示染色体的二进制编码
	value uint16
	//该染色体对应的适应度函数值
	suitability float32
}
type log struct {
	value       uint16
	suitability float32
	Next        *log
}

//定义染色体组
var chromosome_group [COUNT] chromosome
//定义子染色体组
var chromosome_new_group [COUNT] chromosome
//最优染色体
var chromosome_most_suitable chromosome
//适应度没有改变的次数，达到二十次则认为结果已经最优
var suitability_unchange_count int = 0
var head, end *log

func main() {
	isParent := 1
	initialization()
	calculateSuit(isParent)
	sortSuit(isParent)
	for i := 0; i < MAXLOOP; i++ {
		cross()
		calculateSuit(0)
		sortSuit(0)
		selection()
		if complete() {
			break
		}
		record()
		variation()
		calculateSuit(1)
		sortSuit(1)
	}
	writeLog()
	fmt.Println("取值为", Binary_to_Decimal(chromosome_most_suitable.value), "时，达到最大适应度", chromosome_most_suitable.suitability, "。")
}

//将寻找最优解的过程写入文件
func writeLog() {
	file, err := os.Create("./src/main/GeneticLog")
	if err != nil {
		fmt.Println(file, err)
	}
	defer file.Close()
	i := 1
	for ; head.Next != nil; i++ {
		d := strconv.FormatFloat(float64(head.Next.suitability), 'f', -1, 32)
		file.WriteString(d + "  ")
		if i%10 == 0 {
			file.WriteString("\n")
		}
		head = head.Next
	}
	d := strconv.FormatFloat(float64(head.suitability), 'f', -1, 32)
	file.WriteString(d + "  ")
	if i%10 == 0 {
		file.WriteString("\n")
	}
	if complete() {
		file.WriteString("complete!")
	} else {
		file.WriteString("error")
	}
}

//检查是否达到最优
func complete() bool {
	if chromosome_group[0].suitability-end.suitability < MINIMAL_SUB && chromosome_group[0].suitability-end.suitability > -MINIMAL_SUB {
		suitability_unchange_count++
	} else {
		suitability_unchange_count = 0
	}
	if suitability_unchange_count >= UNCHANGE_COUNT_MAX {
		return true
	} else {
		return false
	}
}

//变异操作
func variation() {
	for i := 0; i < COUNT; i++ {
		//判断该染色体是否变异
		if rand_ByProbability(1-power(1-CHANGE_PROBABILITY, 14)) == 1 {
			//选择具体的变异位置
			point := rand_Between_TwoNumber(0, 13)
			mark := uint16(1 << point)
			chromosome_group[i].value = chromosome_group[i].value ^ mark
		}
	}
}

//记录每次的最优情况
func record() {
	logx := log{}
	logx.suitability = chromosome_group[0].suitability
	logx.value = chromosome_group[0].value
	logx.Next = nil
	end.Next = &logx
	end = end.Next
	chromosome_most_suitable = chromosome_group[0]
}

//选择适应度最高的COUNT个染色体作为父总群
func selection() {
	for i, j, k := 0, 0, 0; i < COUNT; i++ {
		if chromosome_group[j].suitability <= chromosome_new_group[k].suitability {
			chromosome_group[i] = chromosome_group[j]
			j++
		} else {
			chromosome_group[i] = chromosome_new_group[k]
			k++
		}
	}
}

//交叉产生新种群
func cross() {
	var flag [COUNT] int
	for i, j := 0, 0; i < COUNT; i++ {
		//随机选择另一条染色体
		if flag[i] == 0 {
			for {
				j = int(rand_Between_TwoNumber(i, COUNT-1))
				if flag[j] == 0 {
					break
				}
			}
		}
		//按概率对染色体i，j进行交叉
		if rand_ByProbability(CROSS_PROBABILITY) == 1 {
			mark1 := create_mark(uint16(rand_Between_TwoNumber(0, 13)))
			mark2 := ^mark1
			chromosome_new_group[i].value = (chromosome_group[i].value)&mark1 + (chromosome_group[j].value)&mark2
			chromosome_new_group[j].value = (chromosome_group[i].value)&mark2 + (chromosome_group[j].value)&mark1
		} else {
			chromosome_new_group[i] = chromosome_group[i]
			chromosome_new_group[j] = chromosome_group[j]
		}
	}
}

//按适应度对中群内个体进行排序
func sortSuit(isParent int) {
	if isParent == 1 {
		for i := 0; i < COUNT; i++ {
			for j := i + 1; j < COUNT; j++ {
				if chromosome_group[i].suitability > chromosome_group[j].suitability {
					temp := chromosome_group[i]
					chromosome_group[i] = chromosome_group[j]
					chromosome_group[j] = temp
				}
			}
		}
	} else {
		for i := 0; i < COUNT; i++ {
			for j := i + 1; j < COUNT; j++ {
				if chromosome_new_group[i].suitability > chromosome_new_group[j].suitability {
					temp := chromosome_new_group[i]
					chromosome_new_group[i] = chromosome_new_group[j]
					chromosome_new_group[j] = temp
				}
			}
		}
	}
}

//计算种群每个个体的适应度
func calculateSuit(isParent int) {
	if isParent == 1 {
		for i := 0; i < COUNT; i++ {
			x := Binary_to_Decimal(chromosome_group[i].value)
			//x^7-600x^6-100x^5+200x^4-300x^3-13579x^2-2468x+123456789
			chromosome_group[i].suitability = x*(x*(x*(x*(x*(x*(x*(x-600))-100)+200)-300)-13579)-2468) + 123456789
			//chromosome_group[i].suitability = (x + 2) * (x - 2)
		}
	} else {
		for i := 0; i < COUNT; i++ {
			x := Binary_to_Decimal(chromosome_new_group[i].value)
			//用于求解函数y=x^7+600x^6-100x^5+200x^4-300x^3-13579x^2-2468x+123456789在（-8，8）之间的最小值
			chromosome_new_group[i].suitability = x*(x*(x*(x*(x*(x*(x*(x-600))-100)+200)-300)-13579)-2468) + 123456789
			//chromosome_new_group[i].suitability = (x + 2) * (x - 2)
		}
	}

}

//初始化染色体
func initialization() {
	//随机数种子，防止每次产生的随机数一样
	rand.Seed(int64(time.Now().Nanosecond()))
	for i := 0; i < COUNT; i++ {
		chromosome_group[i].value = rand_generate_chromosome()
	}
	chromosome_most_suitable.suitability = -100000
	suitability_unchange_count = 0
	//初始化记录链表
	log0 := log{0, 0, nil}
	head = &log0
	end = head
	head = end
}

//随机初始化种群,产生0-16000之间的随机数
func rand_generate_chromosome() uint16 {
	return uint16(rand.Intn(16000) + 1)
}

//实际值转化为二进制表示
func Decimal_to_Binary(x float32) uint16 {
	var y int = int(x)
	return uint16(y*1000 + 8000)
}

//二进制值转化为实际值
func Binary_to_Decimal(x uint16) float32 {
	var y float32 = float32(x)
	return (y - 8000) / 1000
}

//随机产生具体数字之间的一个数,不包括i包括j
func rand_Between_TwoNumber(i int, j int) uint16 {
	if i == j {
		return uint16(i)
	}
	return uint16(i + 1 + rand.Intn(j-i))
}

//根据概率返回1
func rand_ByProbability(p float32) int {
	if float32(rand.Intn(100)) > 99*p {
		return 0
	} else {
		return 1
	}
}

//根据交叉位置生成相应的二进制操作数
func create_mark(number uint16) uint16 {
	return (1 << (number + 1)) - 1
}

//
func power(x float32, y int) float32 {
	result := x
	for y > 1 {
		result = result * x
		y--
	}
	return result
}
