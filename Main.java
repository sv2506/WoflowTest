import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

// "static void main" must be defined in a public class.
public class Main {

    private static String mainURL = "https://nodes-on-nodes-challenge.herokuapp.com/nodes/";

    public static void main(String[] args) {
        try {
            getAllNodes("089ef556-dfff-4ff2-9733-654645be56fe");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getAllNodes(String nodeId) throws Exception {
        Queue<String> nodes = new LinkedList<String>();
        HashMap<String, Integer> visitedNodes = new HashMap<>();
        nodes.add(nodeId);
        visitedNodes.put(nodeId, 1);
        while (!nodes.isEmpty()) {
            String curNode = nodes.pop();
            String currUrl = new URL(mainURL + curNode);
            HttpURLConnection con = (HttpURLConnection) currUrl.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                JSONObject obj = new JSONObject(in);
                String[] child_ids = obj.getJSONArray("child_node_ids");
                in.close();
                for (int i = 0; i < child_ids.length; i++) {
                    curr_child = child_ids[i];
                    if (!visitedNodes.containsKey(curr_child)) {
                        visitedNodes.put(curr_child, 0);
                        nodes.add(curr_child);
                    }
                    visitedNodes.put(curr_child, visitedNodes.get(curr_child) + 1);
                }
            } else {
                System.out.println("GET request not worked");
            }

            String mostFreq = "";
            int maxFreq = 0;
            for (String node : visitedNodes.keySet()) {
                int freq = visitedNodes.get(node);
                if (freq > maxFreq) {
                    mostFreq = node;
                    maxFreq = freq;
                }
            }

            System.out.println("1. Total number of unique nodes = " + visitedNodes.size());
            System.out.println("2. Node ID shared most = " + mostFreq);
        }
    }
}
