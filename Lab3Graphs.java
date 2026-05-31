import java.util.*;
public class Lab3Graphs {

    static int [] [] graph = {
            {1, 2},
            {0, 3 ,4},
            {0, 5, 6},
            {1},
            {1},
            {2},
            {2}
    };
    public static void main(String[] args) {
        System.out.println(" 1. Обхід в глибину (DFS) ");
        boolean[] visitedDFS = new boolean[graph.length];
        System.out.println("Порядок відвідування:");
        dfs(0,visitedDFS);
        System.out.println("\n");

        System.out.println("2. Пошук зв'язних компонент ");
        countConnectedComponents();
        System.out.println();

        System.out.println("3. Обхід у ширину (BFS) ");
        System.out.print("Порядок відвідування: ");
        bfs(0);
        System.out.println("\n");

        System.out.println("4. Пошук найкоротшого шляху ");
        findShortestPath(0, 6);
    }
    public static void dfs(int vertex, boolean[] visited){
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int neighbor : graph[vertex]) {
            if(!visited[neighbor]){
                dfs(neighbor, visited);
            }
        }
    }
    public static void countConnectedComponents(){
        boolean[] visited = new boolean[graph.length];
        int count = 0;
        for(int i = 0; i < graph.length; i++){
            if (!visited[i]){
                count++;
                System.out.print("Компонента " + count + ": ");
                dfs(i, visited);
                System.out.println();

            }
        }
        System.out.println("Загальна кількість зв'язаних компонент:  " + count);
    }
    public static void bfs(int startVertex){
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

        while(!queue.isEmpty()){
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : graph[vertex]) {
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
    public static void findShortestPath(int start, int target){
        boolean[] visited = new boolean[graph.length];
        int[] parent  = new int[graph.length];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while(!queue.isEmpty()){
            int vertex = queue.poll();
            if(vertex == target) break;

            for(int neighbor : graph[vertex]){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    parent[neighbor] = vertex;
                    queue.add(neighbor);
                }
            }
        }
        if (!visited[target]){
            System.out.print("Шляху до вершини " + target + " не існує ");
            return;
        }
        String path = "";
        int edgesCount = 0;
        int current = target;

        while (current != -1){
            path = current + (path.isEmpty() ? "" : " -> " + path);
            current = parent[current];
            if(current != -1) edgesCount++;

        }
        System.out.println("Найкоротший шлях від " + start + " до " + target + ": " + path);
        System.out.println("Довжина шляху: " + edgesCount + " ребер");


    }
}