package adventofcode2022

import adventofcode2022.common.*

fun max(a: Int, b: Int): Int{
    return if(a > b) a else b
}

fun main(args:Array<String>) {
    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/8.txt")

    var n = lines.size
    var m = lines[0].length
    val arr = Array(n) { IntArray(m) }
    
    //left
    for(i in 0..n-1){
        var max = 0
        for(j in 0..m-1){
            if(j > 0){
                max = max(max, lines[i][j-1].digitToInt())
            }

            if(lines[i][j].digitToInt() > max || j == 0){
                arr[i][j] = 1
            }
        }
    }

    //right
    for(i in n-1 downTo 0){
        var max = 0
        for(j in m-1 downTo 0){
            if(j < m-1){
                max = max(max, lines[i][j+1].digitToInt())
            }

            if(lines[i][j].digitToInt() > max || j == m-1){
                arr[i][j] = 1
            }
        }
    }

    //top
    for(j in 0..m-1){
        var max = 0
        for(i in 0..n-1){
            if(i > 0){
                max = max(max, lines[i-1][j].digitToInt())
            }

            if(lines[i][j].digitToInt() > max || i == 0){
                arr[i][j] = 1
            }
        }
    }

    //bottom
    for(j in m-1 downTo 0){
        var max = 0
        for(i in n-1 downTo 0){
            if(i < n-1){
                max = max(max, lines[i+1][j].digitToInt())
            }

            if(lines[i][j].digitToInt() > max || i == n-1){
                arr[i][j] = 1
            }
        }
    }

    var cnt = 0
    for(i in 0..n-1){
        for(j in 0..m-1){
            if(arr[i][j] == 1){
                cnt++
            }
        }
    }

    println (cnt)
}