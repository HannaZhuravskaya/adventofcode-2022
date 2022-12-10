package adventofcode2022

import adventofcode2022.common.*

fun moveTail(head: Point, tail: Point, set: MutableSet<Pair<Int,Int>>){
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

    // bug for case 

    set.add(Pair(tail.x, tail.y))
}

fun main(args:Array<String>) {
    val leftStepRegex = "L (\\d+)".toRegex()
    val rightStepRegex = "R (\\d+)".toRegex()
    val upStepRegex = "U (\\d+)".toRegex()
    val downStepRegex = "D (\\d+)".toRegex()

    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/9.txt")

    var set: MutableSet<Pair<Int,Int>> = mutableSetOf()
    set.add(Pair(0,0))

    var head = Point(0, 0)
    var tail = Point(0, 0)

    for(row in lines){
        var numOfSteps: Int

        if(leftStepRegex.matches(row)){
            numOfSteps = leftStepRegex.find(row)!!.groupValues[1].toInt()

            for(i in 1..numOfSteps){
                head.y = head.y - 1
                moveTail(head, tail, set)
            }
        }
        else if(rightStepRegex.matches(row)){
            numOfSteps = rightStepRegex.find(row)!!.groupValues[1].toInt()

            for(i in 1..numOfSteps){
                head.y = head.y + 1
                moveTail(head, tail, set)
            }
        }
        else if(upStepRegex.matches(row)){
            numOfSteps = upStepRegex.find(row)!!.groupValues[1].toInt()

            for(i in 1..numOfSteps){
                head.x = head.x + 1
                moveTail(head, tail, set)
            }
        }
        else if(downStepRegex.matches(row)){
            numOfSteps = downStepRegex.find(row)!!.groupValues[1].toInt()

            for(i in 1..numOfSteps){
                head.x = head.x - 1
                moveTail(head, tail, set)
            }
        }
    }

    println (set.size)
}

class Point{
    var x: Int
    var y: Int

    constructor(x: Int, y: Int){
        this.x = x
        this.y = y
    }
}