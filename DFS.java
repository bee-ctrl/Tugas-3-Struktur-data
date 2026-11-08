package Tugas;
import java.util.*;

public class DFS {
    private Map<String, List<String>> adjList = new HashMap<>();
    private Map<String, Integer> nodeValues = new HashMap<>();

    public void addEdge(String u, String v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void setNodeValue(String node, int value) {
        nodeValues.put(node, value);
    }

    public boolean dfs(String currentNode, int target, Set<String> visited) {
        visited.add(currentNode);
        int val = nodeValues.get(currentNode);
        
        System.out.println("-> Mengunjungi node " + currentNode + " dengan nilai " + val);

        // Cek apakah nilai node sama dengan target n
        if (val == target) {
            System.out.println(" Sukses! Nilai target " + target + " ditemukan pada node " + currentNode);
            return true;
        }

        // Ambil dan urutkan tetangga agar penelusuran deterministik
        List<String> neighbors = adjList.getOrDefault(currentNode, new ArrayList<>());
        Collections.sort(neighbors);

        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DFS graph = new DFS();
        
        // Inisialisasi Nilai Node (memuat angka 12 dari 2 digit NIM terakhir)
        graph.setNodeValue("a1", 5);
        graph.setNodeValue("a2", 8);
        graph.setNodeValue("a3", 3);
        graph.setNodeValue("a4", 12); // Target n = 12
        graph.setNodeValue("a5", 7);
        graph.setNodeValue("a6", 15);
        graph.setNodeValue("a7", 20);
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
        System.out.println("=== PENCARIAN DFS (Depth-First Search) ===");
        System.out.println("Mencari nilai target n = " + target + " mulai dari node a1\n");
        
        Set<String> visited = new HashSet<>();
        boolean found = graph.dfs("a1", target, visited);
        
        if (!found) {
            System.out.println("Target tidak ditemukan.");
        }
    }
}