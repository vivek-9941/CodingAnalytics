package org.vivek.platform.Service.DTO;

import org.springframework.stereotype.Service;
import org.vivek.platform.Model.Codeforces.Rating;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.Leetcode.LeetCodeSubmissionStats;
import org.vivek.platform.Model.Leetcode.LeetCodeTagStat;
import org.vivek.platform.Model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeetcodeMappingService {

    public Leetcode mapToEntity(LeetcodeDTO dto, User user) {
        Leetcode leetcode = new Leetcode();

        // Basic Info
        leetcode.setHandle(dto.data.matchedUser.username);
        leetcode.setRealName(dto.data.matchedUser.profile.realName);
        leetcode.setGlobalRanking(dto.data.matchedUser.profile.ranking);
        leetcode.setCountry(dto.data.matchedUser.profile.countryName);
        leetcode.setTotalSolved(
                dto.data.matchedUser.submitStatsGlobal.acSubmissionNum
                        .stream()
                        .filter(stat -> stat.difficulty.equalsIgnoreCase("All"))
                        .findFirst()
                        .map(stat -> stat.count)
                        .orElse(0)
        );
        leetcode.setLastUpdated(LocalDateTime.now());
        leetcode.setUser(user);

        // Submission Stats
        LeetCodeSubmissionStats submissionStats = new LeetCodeSubmissionStats();

        for (LeetcodeDTO.SubmissionStat stat : dto.data.matchedUser.submitStatsGlobal.acSubmissionNum) {
            switch (stat.difficulty.toLowerCase()) {
                case "easy":
                    submissionStats.setEasy(stat.count);
                    submissionStats.setEasySubmission(stat.submissions);
                    break;
                case "medium":
                    submissionStats.setMedium(stat.count);
                    submissionStats.setMediumSubmission(stat.submissions);
                    break;
                case "hard":
                    submissionStats.setHard(stat.count);
                    submissionStats.setHardSubmission(stat.submissions);
                    break;
                case "all":
                    submissionStats.setAll_count(stat.count);
                    submissionStats.setAllSubmission(stat.submissions);
                    break;
            }
        }

        submissionStats.setProfile(leetcode);
        leetcode.setSubmissionStats(List.of(submissionStats));

        // Tag Stats - Create one comprehensive LeetCodeTagStat instance
        LeetCodeTagStat tagStat = new LeetCodeTagStat();
        if (dto.data.matchedUser.tagProblemCounts != null) {
            mapTagsToStat(dto.data.matchedUser.tagProblemCounts.fundamental, tagStat);
            mapTagsToStat(dto.data.matchedUser.tagProblemCounts.intermediate, tagStat);
            mapTagsToStat(dto.data.matchedUser.tagProblemCounts.advanced, tagStat);
        }
        tagStat.setProfile(leetcode);
        leetcode.setTagStats(List.of(tagStat));

        // Rating (if applicable, else leave empty)
        leetcode.setRatingHistory(new ArrayList<Rating>());

        return leetcode;
    }

    private void mapTagsToStat(List<LeetcodeDTO.TagInfo> tagInfos, LeetCodeTagStat tagStat) {
        if (tagInfos != null) {
            for (LeetcodeDTO.TagInfo tag : tagInfos) {
                switch (tag.tagName) {
                    // Fundamental tags
                    case "Array" -> tagStat.setArray(tag.problemsSolved);
                    case "Matrix" -> tagStat.setMatrix(tag.problemsSolved);
                    case "String" -> tagStat.setString(tag.problemsSolved);
                    case "Simulation" -> tagStat.setSimulation(tag.problemsSolved);
                    case "Enumeration" -> tagStat.setEnumeration(tag.problemsSolved);
                    case "Sorting" -> tagStat.setSorting(tag.problemsSolved);
                    case "Stack" -> tagStat.setStack(tag.problemsSolved);
                    case "Queue" -> tagStat.setQueue(tag.problemsSolved);
                    case "Linked List" -> tagStat.setLinkedList(tag.problemsSolved);
                    case "Two Pointers" -> tagStat.setTwoPointers(tag.problemsSolved);

                    // Intermediate tags
                    case "Tree" -> tagStat.setTree(tag.problemsSolved);
                    case "Binary Tree" -> tagStat.setBinaryTree(tag.problemsSolved);
                    case "Hash Table" -> tagStat.setHashTable(tag.problemsSolved);
                    case "Ordered Set" -> tagStat.setOrderedSet(tag.problemsSolved);
                    case "Graph" -> tagStat.setGraph(tag.problemsSolved);
                    case "Greedy" -> tagStat.setGreedy(tag.problemsSolved);
                    case "Binary Search" -> tagStat.setBinarySearch(tag.problemsSolved);
                    case "Depth-First Search" -> tagStat.setDepthFirstSearch(tag.problemsSolved);
                    case "Breadth-First Search" -> tagStat.setBreadthFirstSearch(tag.problemsSolved);
                    case "Recursion" -> tagStat.setRecursion(tag.problemsSolved);
                    case "Sliding Window" -> tagStat.setSlidingWindow(tag.problemsSolved);
                    case "Bit Manipulation" -> tagStat.setBitManipulation(tag.problemsSolved);
                    case "Math" -> tagStat.setMath(tag.problemsSolved);

                    // Advanced tags
                    case "Backtracking" -> tagStat.setBacktracking(tag.problemsSolved);
                    case "Quickselect" -> tagStat.setQuickselect(tag.problemsSolved);
                    case "Dynamic Programming" -> tagStat.setDynamicProgramming(tag.problemsSolved);
                    case "Divide and Conquer" -> tagStat.setDivideAndConquer(tag.problemsSolved);
                    case "Trie" -> tagStat.setTrie(tag.problemsSolved);
                    case "Union Find" -> tagStat.setUnionFind(tag.problemsSolved);
                    case "Binary Indexed Tree" -> tagStat.setBinaryIndexedTree(tag.problemsSolved);
                    case "Segment Tree" -> tagStat.setSegmentTree(tag.problemsSolved);
                    case "Monotonic Stack" -> tagStat.setMonotonicStack(tag.problemsSolved);
                }
            }
        }
    }
}