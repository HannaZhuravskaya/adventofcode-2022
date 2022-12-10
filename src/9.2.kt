package adventofcode2022

import adventofcode2022.common.*

fun moveTail(arr: Array<Point>, set: MutableSet<Pair<Int,Int>>){
    for(i in 0..8){
        moveTail(arr[i], arr[i+1])
    }

    set.add(Pair(arr[9].x, arr[9].y))
}

fun mod(a: Int):Int{
    return if(a>0) a else -a
}

fun moveTail(head: Point, tail: Point){
    if(mod(head.x - tail.x) == 2 && mod(head.y - tail.y) == 2){
        if(head.x - tail.x == 2){
            tail.x = tail.x + 1
        }
        if(head.x - tail.x == -2){
            tail.x = tail.x - 1
        }
        if(head.y - tail.y == 2){
            tail.y = tail.y + 1
        }
        if(head.y - tail.y == -2){
            tail.y = tail.y - 1
        }
    }
    else if(head.x - tail.x == 2){
        tail.y = head.y
        tail.x = tail.x + 1
    }
    else if(head.x - tail.x == -2){
        tail.y = head.y
        tail.x = tail.x - 1
    }
    else if(head.y - tail.y == 2){
        tail.y = tail.y + 1
        tail.x = head.x 
    }
    else if(head.y - tail.y == -2){
        tail.x = head.x
        tail.y = tail.y - 1
    }
}

fun main(args:Array<String>) {
    val leftStepRegex = "L (\\d+)".toRegex()
    val rightStepRegex = "R (\\d+)".toRegex()
    val upStepRegex = "U (\\d+)".toRegex()
    val downStepRegex = "D (\\d+)".toRegex()

    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/9.2.txt")

    var set: MutableSet<Pair<Int,Int>> = mutableSetOf()
    set.add(Pair(0,0))

    var arr: Array<Point> = Array(10){ Point(0,0)}

    var maxNum = 0

    for(row in lines){
        var numOfSteps: Int

        if(leftStepRegex.matches(row)){
            numOfSteps = leftStepRegex.find(row)!!.groupValues[1].toInt()
            maxNum += numOfSteps

            for(i in 1..numOfSteps){
                arr[0].y = arr[0].y - 1
                moveTail(arr, set)
            }
        }
        else if(rightStepRegex.matches(row)){
            numOfSteps = rightStepRegex.find(row)!!.groupValues[1].toInt()
            maxNum += numOfSteps

            for(i in 1..numOfSteps){
                arr[0].y = arr[0].y + 1
                moveTail(arr, set)
            }
        }
        else if(upStepRegex.matches(row)){
            numOfSteps = upStepRegex.find(row)!!.groupValues[1].toInt()
            maxNum += numOfSteps

            for(i in 1..numOfSteps){
                arr[0].x = arr[0].x + 1
                moveTail(arr, set)
            }
        }
        else if(downStepRegex.matches(row)){
            numOfSteps = downStepRegex.find(row)!!.groupValues[1].toInt()
            maxNum += numOfSteps

            for(i in 1..numOfSteps){
                arr[0].x = arr[0].x - 1
                moveTail(arr, set)
            }
        }
    }

    println (set.size)
}

data class Point(var x: Int, var y: Int){
}