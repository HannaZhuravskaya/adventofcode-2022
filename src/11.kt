package adventofcode2022

import adventofcode2022.common.*
import java.util.LinkedList

fun readMonkeys(lines:List<String>): MutableList<Monkey>{
    val itemsRegex = "Starting items: ([0-9, ]+)".toRegex()
    val operationRegex = "Operation: new = old ([*+]{1}) ([0-9-]+|old)".toRegex()
    val testRegex = "Test: divisible by ([0-9-]+)".toRegex()
    val trueRegex = "If true: throw to monkey (\\d+)".toRegex()
    val falseRegex = "If false: throw to monkey (\\d+)".toRegex()

    var monkeys:MutableList<Monkey> = mutableListOf()

    for(i in 0..lines.size-1 step 7){
        var items = itemsRegex.find(lines[i+1])!!.groupValues[1].split(", ").map { it.toInt() }
        var operationParams = operationRegex.find(lines[i+2])!!
        var operation = operationParams.groupValues[1]
        var operand = operationParams.groupValues[2]
        var testValue = testRegex.find(lines[i+3])!!.groupValues[1].toInt()
        var monkeyIfTrue = trueRegex.find(lines[i+4])!!.groupValues[1].toInt()
        var monkeyIfFalse = falseRegex.find(lines[i+5])!!.groupValues[1].toInt()

        var monkey = Monkey(operation, operand, testValue, monkeyIfTrue, monkeyIfFalse)
        monkey.items.addAll(items)
        monkeys.add(monkey)
    }

    return monkeys
}

fun main(args:Array<String>) { 
    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/11.txt")
    var monkeys = readMonkeys(lines)

    var monkeysNumOfInspects: MutableList<Int> = MutableList(monkeys.size){ 0 }

    for(i in 1..20){
        for((j,monkey) in monkeys.withIndex()){
            var cnt: Int = monkeys[j].items.size
            while(cnt > 0){
                monkeysNumOfInspects[j] = monkeysNumOfInspects[j] + 1
                val newValue = monkey.executeOperation(monkey.items[0])
                val monkeyNum = monkey.getMonkeyToThrowItemTo(newValue)
                monkeys[monkeyNum].items.addLast(newValue)
                monkey.items.removeFirst()
                --cnt
            }
        }
    }

    monkeysNumOfInspects.sortBy { -it }
    println(monkeysNumOfInspects[0] * monkeysNumOfInspects[1])
}

class Monkey(var operation: String,var secondOperand: String,var testValue: Int,var monkeyIfTrue: Int,var monkeyIfFalse: Int){
    var items:LinkedList<Int> = LinkedList<Int>()
    
    fun executeOperation(value: Int): Int{

        var operand = if(secondOperand == "old") value else secondOperand.toInt()

        if(operation == "+"){
            return (value + operand) / 3 
        }
        if(operation == "*"){
            return (value * operand) / 3
        }

        throw IllegalArgumentException("Unknown operation")
    }

    fun getMonkeyToThrowItemTo(item: Int): Int{
        if(item % testValue == 0){
            return monkeyIfTrue
        }
        else{
            return monkeyIfFalse
        }
    }
}