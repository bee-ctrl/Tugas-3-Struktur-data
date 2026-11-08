package Tugas;
import java.util.*;

public class BFS {
    private Map<String, List<String>> adjList = new HashMap<>();
    private Map<String, Integer> nodeValues = new HashMap<>();

    public void addEdge(String u, String v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void setNodeValue(String node, int value) {
        nodeValues.put(node, value);
    }

    public boolean bfs(String startNode, int target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            int val = nodeValues.get(currentNode);

            System.out.println("-> Mengunjungi node " + currentNode + " dengan nilai " + val);

            if (val == target) {
                System.out.println(" Sukses! Nilai target " + target + " ditemukan pada node " + currentNode);
                return true;
            }

            List<String> neighbors = adjList.getOrDefault(currentNode, new ArrayList<>());
            Collections.sort(neighbors);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BFS graph = new BFS();
        
        // Inisialisasi Nilai Node (memuat angka 12 dari 2 digit NIM terakhir)
        graph.setNodeValue("a1", 5);
        graph.setNodeValue("a2", 8);
        graph.setNodeValue("a3", 3);
        graph.setNodeValue("a4", 13); // Target n = 12
        graph.setNodeValue("a5", 7);
        graph.setNodeValue("a6", 15);
        graph.setNodeValue("a7", 12);
        graph.setNodeValue("a8", 9);

        // Inisialisasi Hubungan (Edge)
        graph.addEdge("a1", "a2");
        graph.addEdge("a1", "a3");
        graph.addEdge("a2", "a4");
        graph.addEdge("a2", "a5");
        graph.addEdge("a3", "a6");
        graph.addEdge("a3", "a7");
        graph.addEdge("a4", "a8");

        int target = 12; // 2 digit NIM akhir
        System.out.println("=== PENCARIAN BFS (Breadth-First Search) ===");
        System.out.println("Mencari nilai target n = " + target + " mulai dari node a1\n");
        
        boolean found = graph.bfs("a1", target);
        
        if (!found) {
            System.out.println("Target tidak ditemukan.");
        }
    }
}