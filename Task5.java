import java.util.*;

public class Task5 {
    public static void main(String[] args) {
        AdjacencyMatrixGraph AMG;
        AdjacencyListGraph ALG;
        // This next commented are for allowing the user to enter the graph himself, the
        // test cases highlighted in the portofolio are hard coded

        // try (Scanner sc = new Scanner(System.in)) {
        // System.out.print("Enter the number of nodes: ");
        // int nodes_num = sc.nextInt();
        // AMG = new AdjacencyMatrixGraph(nodes_num);
        // ALG = new AdjacencyListGraph(nodes_num);
        // for (int i = 0; i < nodes_num; i++) {
        // System.out.print("Enter the number of nodes connected to node number " + i +
        // " : ");
        // int nodes_num_connected = sc.nextInt();
        // for (int j = 0; j < nodes_num_connected; j++) {
        // System.out.print("Enter the end node: ");
        // int end = sc.nextInt();
        // System.out.print("Enter the weight: ");
        // int weight = sc.nextInt();
        // AMG.add_edge(i, end, weight);
        // ALG.add_edge(i, end, weight);
        // }
        // }
        // }

        AMG = new AdjacencyMatrixGraph(5);
        AMG.add_edge(0, 1, 5);
        AMG.add_edge(0, 2, 2);
        AMG.add_edge(1, 3, 1);
        AMG.add_edge(1, 4, 7);
        AMG.add_edge(2, 3, 5);
        AMG.add_edge(2, 4, 8);
        AMG.add_edge(3, 4, 5);
        AMG.print_graph();
        ALG = new AdjacencyListGraph(5);
        ALG.add_edge(0, 1, 5);
        ALG.add_edge(0, 2, 2);
        ALG.add_edge(1, 3, 1);
        ALG.add_edge(1, 4, 7);
        ALG.add_edge(2, 3, 5);
        ALG.add_edge(2, 4, 8);
        ALG.add_edge(3, 4, 5);
        ALG.print_graph();
        ALG.BFS(0);
        ALG.DFS(0);
        // This is the undirected graph used by prims algorithm for task 7

        int[][] G = { { 0, 7, 0, 8, 0, 0 }, { 7, 0, 6, 3, 0, 0 }, { 0, 6, 0, 4, 2, 5 }, { 8, 3, 4, 0, 3, 0 },
                { 0, 0, 2, 3, 0, 2 }, { 0, 0, 5, 0, 2, 0 } };
        AdjacencyMatrixGraph AMG2 = new AdjacencyMatrixGraph(G.length);
        AMG2.adjacency_matrix = G;
        AMG2.Prim();

    }
}

class AdjacencyMatrixGraph { // Adjacency matrix graph implimentation for task 5
    public int adjacency_matrix[][];
    public int nodes_total;

    public AdjacencyMatrixGraph(int num_vertices) {
        nodes_total = num_vertices;
        adjacency_matrix = new int[num_vertices][num_vertices];
        for (int i = 0; i < num_vertices; i++) {
            for (int j = 0; j < num_vertices; j++) {
                adjacency_matrix[i][j] = -1;
            }
        }
    }

    public void add_edge(int start, int end, int weight) {
        adjacency_matrix[start][end] = weight;
    }

    public void print_graph() {
        StringBuilder matrix_head = new StringBuilder();
        matrix_head.append("   ");
        for (int i = 0; i < nodes_total; i++) {
            matrix_head.append(i + " ");
        }
        System.out.println(matrix_head);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < nodes_total; i++) {
            s.delete(0, s.length());
            s.append(i + ": ");
            for (int j = 0; j < nodes_total; j++) {
                s.append(adjacency_matrix[i][j] + ", ");
            }
            System.out.println(s);
        }
    }

    public void Prim() { // Prims algorithm for task 7, finds the maximum spanning tree
        boolean[] viseted = new boolean[nodes_total];
        int start_node = 0;
        viseted[start_node] = true;
        System.out.println("Nodes : Weight");
        while (start_node < nodes_total - 1) {
            int max = Integer.MIN_VALUE;
            int x = 0; // row number
            int y = 0; // col number
            for (int i = 0; i < nodes_total; i++) {
                if (viseted[i] == true) {
                    for (int j = 0; j < nodes_total; j++) {
                        if (!viseted[j] && adjacency_matrix[i][j] != -1) {
                            if (max < adjacency_matrix[i][j]) {
                                max = adjacency_matrix[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
            System.out.println(x + " - " + y + " :  " + adjacency_matrix[x][y]);
            viseted[y] = true;
            start_node++;
        }
    }
}

class AdjacencyListNodes { // Adjacency matrix list nodes, used for the linked list in the adjacency list
    public int start;
    public int end;
    public int weight;

    public AdjacencyListNodes(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

class AdjacencyListGraph { // Adjacency list graph implimentation for task 5
    int nodes_total;
    LinkedList<AdjacencyListNodes>[] adjacency_list;

    AdjacencyListGraph(int nodes_total) {
        this.nodes_total = nodes_total;
        adjacency_list = new LinkedList[nodes_total];
        for (int i = 0; i < nodes_total; i++) {
            adjacency_list[i] = new LinkedList<>();
        }
    }

    public void add_edge(int start, int end, int weight) {
        AdjacencyListNodes node = new AdjacencyListNodes(start, end, weight);
        adjacency_list[start].add(node);
    }

    public void print_graph() {
        for (int i = 0; i < nodes_total; i++) {
            LinkedList<AdjacencyListNodes> list = adjacency_list[i];
            for (int j = 0; j < list.size(); j++) {
                System.out.println("Node " + i + " and Node " +
                        list.get(j).end + " connected with Weight " + list.get(j).weight);
            }
        }
    }

    public void BFS(int node_number) { // BFS function for Task 6
        boolean visited[] = new boolean[nodes_total];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[node_number] = true;
        queue.add(node_number);
        while (queue.size() != 0) {
            node_number = queue.poll();
            System.out.print(node_number + " ");
            Iterator<AdjacencyListNodes> i = adjacency_list[node_number].listIterator();
            while (i.hasNext()) {
                AdjacencyListNodes n = i.next();
                if (!visited[n.end]) {
                    visited[n.end] = true;
                    queue.add(n.end);
                }
            }

        }
    }

    private void DFSUtil(int node_number, boolean visited[]) { // DFS function for Task 6

        visited[node_number] = true;
        System.out.print(node_number + " ");
        Iterator<AdjacencyListNodes> i = adjacency_list[node_number].listIterator();
        while (i.hasNext()) {
            AdjacencyListNodes n = i.next();
            if (!visited[n.end]) {
                DFSUtil(n.end, visited);
            }
        }
    }

    public void DFS(int start_node) { // DFS function for Task 6
        boolean visited[] = new boolean[nodes_total];
        DFSUtil(start_node, visited);
    }

}
