package adventofcode2022

import java.io.File
import java.util.ArrayDeque

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun buildFileSystem(lines: List<String>): Folder{
    val goToChildRegex = "[$]{1} cd (.+)".toRegex()
    val goToParentRegex = "[$]{1} cd ..".toRegex()
    val goToRootRegex = "[$]{1} cd /".toRegex()
    val addChildrenRegex = "[$]{1} ls".toRegex()
    val addFolderRegex = "dir (.+)".toRegex()
    val addFileRegex = "(\\d+) (.+)".toRegex()

    var root = Folder("/", null);
    var cur = root;

    //check if exists

    var addMode = false

    for(row in lines){
        if(goToRootRegex.matches(row)){
            cur = root
            addMode = false
        }
        else if(goToParentRegex.matches(row)){
            cur = if(cur.parent != null) cur.parent!! else cur
            addMode = false
        }
        else if(goToChildRegex.matches(row)){
            var childFolderName = goToChildRegex.find(row)!!.groupValues[1]

            if(cur.subFolders.get(childFolderName) == null){
                addMode = true
                cur.addSubFolder(Folder(childFolderName, cur))
            }

            cur = cur.subFolders.getValue(childFolderName)
            addMode = false
        }
        else if(addChildrenRegex.matches(row)){
            addMode = true
        }
        else if(addFolderRegex.matches(row) && addMode == true){
            var folderName = addFolderRegex.find(row)!!.groupValues[1]
            if(cur.subFolders.get(folderName) == null){
                cur.addSubFolder(Folder(folderName, cur))
            }
        }
        else if(addFileRegex.matches(row) && addMode == true){
            var matchResult = addFileRegex.find(row)!!
            val fileSize = matchResult.groupValues[1].toInt()
            val fileName = matchResult.groupValues[2]
            if(cur.files.get(fileName) == null){
                cur.addFile(fileSize, fileName)
            }
        }
    }

    return root;
}

fun findSubfoldersSize(folder: Folder, curSum: Int): Int{

    if(folder.subFolders.isEmpty()){
        return if(folder.size <= 100000) folder.size else 0
    }
    
    var newSum = 0
    for(subFolder in folder.subFolders.values){
        newSum += findSubfoldersSize(subFolder, newSum)
    }

    if(folder.size <= 100000){
        newSum += folder.size
    }
    
    return newSum
}

fun main(args:Array<String>) {
    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/7.txt")
    var root = buildFileSystem(lines)
    var total = findSubfoldersSize(root, 0)
    println(total)
}

class Folder{
    var files: MutableMap<String, Int> = mutableMapOf()
    var subFolders: MutableMap<String, Folder> = mutableMapOf()
    var size: Int = 0
    var name: String
    var parent: Folder?

    constructor(name: String, parent: Folder?) {
        this.name = name
        this.parent = parent
    }

    fun addFile(size: Int, name: String) {
        files.put(name, size)
        this.size += size

        var cur = this
        while(cur.parent != null){
            cur = cur.parent!!
            cur.size += size
        }
    }

    fun addSubFolder(subFolder: Folder){
        subFolders.put(subFolder.name, subFolder)
        this.size += subFolder.size

        var cur = this
        while(cur.parent != null){
            cur = cur.parent!!
            cur.size += subFolder.size
        }
    }
}