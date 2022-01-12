package pl.as.qora.tree.boundary;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import pl.as.qora.tree.entity.NodeType;
import pl.as.qora.tree.entity.TreeNode;

@Path("tree")
public class TreeResource {
    
    @GET
    public List<TreeNode> root() {
        TreeNode root = new TreeNode("db", NodeType.ROOT, 
            List.of(new TreeNode("Instance", NodeType.INSTANCE, List.of()),
                        new TreeNode("Schemas", NodeType.SCHEMAS, List.of()),
                        new TreeNode("Security", NodeType.SECURITY, List.of()),
                        new TreeNode("Storage", NodeType.STORAGE, List.of())));
        return List.of(root);
    }

    @GET
    @Path("{node}")
    public List<TreeNode> children(@PathParam("node") NodeType node) {
        return switch (node) {
            case INSTANCE -> instanceNodes();
            default -> List.of();
        };
    }    

    private List<TreeNode> instanceNodes() {
        return List.of();
    }
}
