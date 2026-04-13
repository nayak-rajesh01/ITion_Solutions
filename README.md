# ITion Solutions — Software Developer Assignment

**Submitted by:** Rajesh Kumar Nayak
**Submission Date:** April 13, 2026
**Position:** Software Developer

---

## About Me

**Rajesh Kumar Nayak**
MCA Graduate (2025) | Marathahalli, Karnataka, India
- 📧 rajeshnayakn097@gmail.com
- 📱 +91 9827379283 / +91 6371425464
- 💻 [LeetCode](https://leetcode.com/u/rajeshroot1/)
- 🐙 [GitHub](https://github.com/nayak-rajesh01/)

---

## Assignment Overview

This repository contains solutions to the three programming problems provided by ITion Solutions as part of the Software Developer hiring process. All solutions are written in **pure Java** with no external libraries (except standard JDK), following clean code and OOP principles.

---

## Problem 1 — Binary Tree: Nodes at Distance K from Root

### Problem Statement
Given the root of a binary tree and an integer `k`, recursively print all nodes at distance `k` from the root.

### Approach
- Use a recursive method `printNodesAtDistanceK(Node root, int k)`
- **Base case:** if `root == null`, return immediately
- **Target reached:** if `k == 0`, print `root.data`
- **Recurse:** call left and right subtrees with `k - 1`
- No extra data structures needed — the call stack itself tracks depth

**Time Complexity:** O(N) — every node is visited once
**Space Complexity:** O(H) — recursion stack, where H = height of the tree

### Output
```
=== Question 1: Nodes at Distance K from Root ===

Tree structure:
            1
          /   \
         2     3
        / \   / \
       4   5 6   7
      /
     8

Nodes at distance 0: 1
Nodes at distance 1: 2 3
Nodes at distance 2: 4 5 6 7
Nodes at distance 3: 8
Nodes at distance 4:
```

---

## Problem 2 — Bitcoin Price in INR (in Words)

### Problem Statement
Make an HTTP GET request to the CoinGecko public REST API and print the Bitcoin price in INR written out in Indian number words (Crores, Lakhs, Thousands).

**API URL:**
```
https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd,inr,eur
```

### Approach
- Use `java.net.HttpURLConnection` for the HTTP GET request — no third-party library
- Manually parse the JSON response string to extract the `inr` value
- Convert the integer using the **Indian numbering system:**
    - 1,00,00,000 = 1 Crore
    - 1,00,000 = 1 Lakh
    - 1,000 = 1 Thousand
    - 100 = 1 Hundred
- Decompose using integer division and modulo into groups, then map each to word form

**Time Complexity:** O(log N) — fixed number of digit-group operations
**Space Complexity:** O(1)

### Output
```
=== Question 2: Bitcoin Price in INR (in Words) ===

Fetching Bitcoin prices from CoinGecko API...
Raw API Response: {"bitcoin":{"usd":72142,"inr":6781914,"eur":61484}}

Bitcoin Prices:
  USD : $72,142
  EUR : €61,484
  INR : ₹6,781,914

Bitcoin Price in INR (in words):
  ₹6781914  →  Sixty Seven Lakh Eighty One Thousand Nine Hundred and Fourteen

--- Conversion examples ---
  7823123    → Seventy Eight Lakh Twenty Three Thousand One Hundred and Twenty Three
  100000     → One Lakh
  10000000   → One Crore
  85000000   → Eight Crore Fifty Lakh
  999999999  → Ninety Nine Crore Ninety Nine Lakh Ninety Nine Thousand Nine Hundred and Ninety Nine
```

---

## Problem 3 — Orchard Sizes (Connected Components in a Matrix)

### Problem Statement
Given a matrix of `'T'` (Tree) and `'O'` (no tree) characters, find the sizes of all orchards — groups of trees connected vertically, horizontally, **or diagonally** (8-directional connectivity).

**Input (hardcoded):**
```
O T O O
O T O T
T T O T
O T O T
```

### Approach
- **Flood Fill / DFS** with 8-directional traversal
- Iterate every cell; when an unvisited `'T'` is found, launch a DFS
- DFS marks cells visited (`'T'` → `'V'`) and counts all connected trees recursively
- Collect all orchard sizes, sort descending, and print

**Time Complexity:** O(R × C) — each cell visited at most once
**Space Complexity:** O(R × C) — recursion stack in the worst case

### Output
```
=== Question 3: Orchard Sizes ===

Input Matrix:
O T O O
O T O T
T T O T
O T O T

Orchard Sizes (descending): 5, 3

Explanation:
  Orchard 1 → cells (0,1),(1,1),(2,0),(2,1),(3,1) — connected via adjacency/diagonal → size 5
  Orchard 2 → cells (1,3),(2,3),(3,3)             — connected vertically           → size 3

Additional Test (all diagonally connected):
T O T
O T O
T O T
Orchard Sizes: 5  (all 5 trees form one orchard via diagonal connections)
```

---

## Repository Structure

```
ITion_Solutions/
├── src/
│   ├── q1/
│   │   └── Question1_BinaryTreeDistanceK.java
│   ├── q2/
│   │   └── Question2_BitcoinPriceINR.java
│   └── q3/
│       └── Question3_OrchardSizes.java
├── .gitignore
├── ITion_Solutions.iml
└── README.md
```

---

## How to Run

**Prerequisites:** Java 8 or above

```bash
# Compile
javac Question1_BinaryTreeDistanceK.java
javac Question2_BitcoinPriceINR.java
javac Question3_OrchardSizes.java

# Run
java Question1_BinaryTreeDistanceK
java Question2_BitcoinPriceINR
java Question3_OrchardSizes
```

> **Note for Q2:** An active internet connection is required to fetch live Bitcoin prices from the CoinGecko API. If the network is unavailable, the program gracefully falls back to a demonstration with example values.

---

## Technical Skills Used

`Java` · `Recursion` · `Binary Trees` · `REST API` · `HTTP Client` · `JSON Parsing` · `DFS / Flood Fill` · `Graph Algorithms` · `Data Structures` · `OOP`