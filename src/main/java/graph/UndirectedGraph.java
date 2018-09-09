package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 无向图
 * @author dewey
 * @date 2018/9/9 21:17
 */
public class UndirectedGraph {
    public static void main(String[] args) {
        int[][] edgs = CommonData.edges;
        int[] visited = new int[CommonData.edgeCount];
        List<Integer> dfsPath = new ArrayList<>();
        dfs(edgs,visited,1,dfsPath);
        System.out.println(dfsPath.toString());
        List<Integer> bfsPath = new ArrayList<>();
        visited = new int[CommonData.edgeCount];
        System.out.println(bfs(edgs,visited,1,bfsPath));
        System.out.println(bfsPath.toString());
    }

    /**
     * 无向图dfs，存储无向图遍历路径
     * @param edges
     * @param visited
     * @param start
     * @param result
     */
    public static void dfs(int[][] edges,int[] visited,int start,List<Integer> result){
        visited[start] = 1;
        result.add(start);
        for (int i = 1; i < visited.length; i++) {
            if(edges[start][i]==1&&visited[i]==0&&start!=i){
                dfs(edges,visited,i,result);
            }
        }
    }

    /**
     * 无向图bfs，存储遍历路径，并返回图的深度
     * @param edges
     * @param visited
     * @param start
     * @param path
     * @return
     */
    public static int bfs(int[][] edges,int[] visited,int start,List<Integer> path){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int deep = 0;
        int levelCountNow = 1;
        int levelCountNext = 0;
        while(!queue.isEmpty()){
            int temp = queue.poll();
            path.add(temp);
            visited[temp]=1;
            levelCountNow--;
            for (int i = 0; i < visited.length; i++) {
                if(i!=temp&&visited[i]==0&&edges[temp][i]==1){
                    queue.add(i);
                    levelCountNext++;
                }
            }
            if(levelCountNow==0){
                deep++;
                levelCountNow=levelCountNext;
                levelCountNext = 0;
            }
        }
        return deep-1;
    }
}
