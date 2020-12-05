@file:JvmName("Main")

package com.github.aoc2020.day5_kotlin

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    var id2 = seat_id ("FBFBBFFRLR");
    println("seat id: $id2");
    val input = readFile("input.txt")
    val sorted = input.stream().map {s -> seat_id(s) }.sorted().collect(Collectors.toList());
    val set = input.stream().map {s -> seat_id(s) }.sorted().collect(Collectors.toSet());
    sorted.forEach {s -> println("RES: $s")};
    var first = sorted.first();
    var last = sorted.last();
    println("$first $last")
    (first..last).filter { x -> !set.contains(x) }.forEach { x -> println("Missing: $x") };
}

fun seat_id(str: String) : Int {
    var row = str.substring(0..6);
    var seat = str.substring(7..9);
    return id(findRow(row), findSeat(seat));
}

fun findRow(id: String) : Int {
    var part = 128;
    var base = 0;
    for (c in id.iterator()) {
        if (c == 'B') base += (part / 2);
        part = part/2;
    }
    return base;
}

fun findSeat(id: String) : Int {
    var part = 8;
    var base = 0;
    for (c in id.iterator()) {
        if (c == 'R') base += (part / 2);
        part = part/2;
    }
    return base;
}

fun id(row: Int, seat: Int) : Int {
    return row * 8 + seat;
}

fun readFile(fileName: String): List<String> {
    return Files.lines(Path.of(fileName))
        .collect(Collectors.toUnmodifiableList())
}