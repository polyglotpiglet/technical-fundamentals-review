package com.ojha.algorithms.graphs

import scala.collection.mutable

case class Node[T](value: T)

case class Edge[T](node1: Node[T], node2: Node[T])

case class Graph[T](nodes: Seq[Node[T]], edges: Seq[Edge[T]]) {

  private val nodesToTheirChildren = edges.flatMap(edge => Seq(edge.node1 -> edge.node2, edge.node2 -> edge.node1))
    .groupBy(_._1)
    .map(entry => entry._1 -> entry._2.map(_._2))

  def dfs(startingPoint: Node[T]): Seq[T] = {
    val s = new mutable.ArrayStack[Node[T]]()
    s.push(startingPoint)
    val visited = mutable.HashSet[Node[T]]()
    var result = Seq[T]()

    while (s.nonEmpty) {
      val current = s.pop()
      if (!visited.contains(current)) {
        val children = nodesToTheirChildren.getOrElse(current, Seq.empty)
        children
            .filter(child => !visited.contains(child))
            .foreach(s.push)

        result = result :+ current.value
        visited.add(current)

      }

    }

    result
  }


  def bfs(startingPoint: Node[T]): Seq[T] = {
    val q = new mutable.Queue[Node[T]]()
    q.enqueue(startingPoint)
    val visited = mutable.HashSet[Node[T]]()
    var result = Seq[T]()
    while (q.nonEmpty) {
      val current = q.dequeue()
      if (!visited.contains(current)) {
        result = result :+ current.value
        visited.add(current)
      }
      nodesToTheirChildren.getOrElse(current, Seq.empty)
        .filter(child => !visited.contains(child))
        .foreach(node => q.enqueue(node))
    }
    result
  }
}

