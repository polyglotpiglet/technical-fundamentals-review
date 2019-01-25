package com.ojha.algorithms.tree

import com.ojha.algorithms.trees.{Tree, TreeNode}
import org.scalatest.FlatSpec

class TreeSpec extends FlatSpec {

  /*
         1
   2               3
4    5          6

 */

  import TreeNode._

  private val four = TreeNode(4)
  private val five = TreeNode(5)
  private val six = TreeNode(6)
  private val two = treeNode(2, four, five)
  private val three = treeNode(3, six)
  private val one = treeNode(1, two, three)

  private val tree = Tree(one)

  "Trees" should "do a preorder dfs" in {
    val expected = Seq(1, 2, 4, 5, 3, 6)
    assert(tree.recursivePreorderDfs() == expected)
    assert(tree.iterativePreorderDfs() == expected)
  }

  "Trees" should "do an inorder dfs" in {
    val expected = Seq(4, 2, 5, 1, 6, 3)
    assert(tree.recursiveInorderDfs() == expected)
    assert(tree.iterativeInorderDfs() == expected)
  }

}
