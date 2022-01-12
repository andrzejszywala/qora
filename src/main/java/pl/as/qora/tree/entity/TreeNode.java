package pl.as.qora.tree.entity;

import java.util.List;

public record TreeNode (
    String name,
    NodeType type,
    List<TreeNode> children) {

}
