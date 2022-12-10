package adventofcode2022

import adventofcode2022.common.*

fun main(args:Array<String>) {
    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/8.2.txt")

    var n = lines.size
    var m = lines[0].length
    val arr = Array(n) { IntArray(m) }
    
    for(i in 0..n-1){
        for(j in 0..m-1){
            var curTree = lines[i][j].digitToInt()
            var value = 1
            
            // left
            var cnt = 0
            var k = j - 1
            while(k > -1){
                ++cnt
                
                if(lines[i][k].digitToInt() >= curTree){
                    break
                }

                --k
            }
            value *= cnt

            // right
            cnt = 0
            k = j + 1
            while(k < m){
                ++cnt

                if(lines[i][k].digitToInt() >= curTree){
                    break
                }

                ++k
            }
            value *= cnt

            // top
            cnt = 0
            k = i - 1
            while(k > -1){
                ++cnt

                if(lines[k][j].digitToInt() >= curTree){
                    break
                }

                --k
            }
            value *= cnt

            // bottom
            cnt = 0
            k = i + 1
            while(k < n){
                ++cnt

                if(lines[k][j].digitToInt() >= curTree){
                    break
                }

                ++k
            }
            value *= cnt

            arr[i][j] = value
        }
    }

    var max = 0
    for(i in 0..n-1){
        for(j in 0..m-1){
            if(arr[i][j] > max){
                max = arr[i][j]
            }
        }
    }

    println (max)
}