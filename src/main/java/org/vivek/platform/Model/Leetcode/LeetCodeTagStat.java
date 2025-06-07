package org.vivek.platform.Model.Leetcode;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "leetcode_tag_stat")
public class LeetCodeTagStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fundamental Tags
    @Column(name = "array")
    private Integer array;

    @Column(name = "matrix")
    private Integer matrix;

    @Column(name = "string")
    private Integer string;

    @Column(name = "simulation")
    private Integer simulation;

    @Column(name = "enumeration")
    private Integer enumeration;

    @Column(name = "sorting")
    private Integer sorting;

    @Column(name = "stack")
    private Integer stack;

    @Column(name = "queue")
    private Integer queue;

    @Column(name = "linked_list")
    private Integer linkedList;

    @Column(name = "two_pointers")
    private Integer twoPointers;

    // Intermediate Tags
    @Column(name = "tree")
    private Integer tree;

    @Column(name = "binary_tree")
    private Integer binaryTree;

    @Column(name = "hash_table")
    private Integer hashTable;

    @Column(name = "ordered_set")
    private Integer orderedSet;

    @Column(name = "graph")
    private Integer graph;

    @Column(name = "greedy")
    private Integer greedy;

    @Column(name = "binary_search")
    private Integer binarySearch;

    @Column(name = "depth_first_search")
    private Integer depthFirstSearch;

    @Column(name = "breadth_first_search")
    private Integer breadthFirstSearch;

    @Column(name = "recursion")
    private Integer recursion;

    @Column(name = "sliding_window")
    private Integer slidingWindow;

    @Column(name = "bit_manipulation")
    private Integer bitManipulation;

    @Column(name = "math")
    private Integer math;

    // Advanced Tags
    @Column(name = "backtracking")
    private Integer backtracking;

    @Column(name = "quickselect")
    private Integer quickselect;

    @Column(name = "dynamic_programming")
    private Integer dynamicProgramming;

    @Column(name = "divide_and_conquer")
    private Integer divideAndConquer;

    @Column(name = "trie")
    private Integer trie;

    @Column(name = "union_find")
    private Integer unionFind;

    @Column(name = "binary_indexed_tree")
    private Integer binaryIndexedTree;

    @Column(name = "segment_tree")
    private Integer segmentTree;

    @Column(name = "monotonic_stack")
    private Integer monotonicStack;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Leetcode profile;
}
