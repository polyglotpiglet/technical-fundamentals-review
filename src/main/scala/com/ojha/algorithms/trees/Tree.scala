package com.ojha.algorithms.trees

import scala.collection.mutable

case class TreeNode[T](value: T, left: Option[TreeNode[T]] = None, right: Option[TreeNode[T]] = None)

object TreeNode {
  def treeNode[T](value: T, left: TreeNode[T], right: TreeNode[T]): TreeNode[T] = TreeNode(value, Some(left), Some(right))
  def treeNode[T](value: T, left: TreeNode[T]): TreeNode[T] = TreeNode(value, Some(left))
}

case class Tree[T](root: TreeNode[T]) {
  def iterativeInorderDfs(): Seq[T] = {
    var result = Seq.empty[T]
    val stack = new mutable.ArrayStack[TreeNode[T]]()
    var visited = Set.empty[TreeNode[T]]
    stack.push(root)
    while (stack.nonEmpty) {
      val current = stack.pop()

      if (current.left.isDefined && !visited.contains(current.left.get)) {
        stack.push(current)
        stack.push(current.left.get)
      }
      else {
        result = result :+ current.value
        visited = visited + current
        current.right.foreach(right => stack.push(right))

      }
    }
    result
  }

  def recursiveInorderDfs(): Seq[T] = recursiveInorderDfs(root)

  private def recursiveInorderDfs(node: TreeNode[T], results: Seq[T] = Seq.empty): Seq[T] = {
    val updated = node.left.map(left => recursiveInorderDfs(left, results)).getOrElse(results)
    val updated2 = updated :+ node.value
    node.right.map(right => recursiveInorderDfs(right, updated2)).getOrElse(updated2)
  }

  def iterativePreorderDfs(): Seq[T] = {
    var result = Seq.empty[T]
    val stack = new mutable.ArrayStack[TreeNode[T]]()
    stack.push(root)
    while (stack.nonEmpty) {
      val current = stack.pop()
      result = result :+ current.value
      current.right.foreach(right => stack.push(right))
      current.left.foreach(left => stack.push(left))
    }
    result
  }

  def recursivePreorderDfs(): Seq[T] = preOrderDfs(root)

  private def preOrderDfs(node: TreeNode[T], results: Seq[T] = Seq.empty): Seq[T] = {
    val updated = results :+ node.value
    val updated2 = node.left.map(left => preOrderDfs(left, updated)).getOrElse(updated)
    node.right.map(right => preOrderDfs(right, updated2)).getOrElse(updated2)
  }

}

