package ru.altmanea.edu.server.repo

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import ru.altmanea.edu.server.model.*
import java.io.FileInputStream

var importFile: String = "НагрузкаАМ.xlsx"
var all: List<Inform> = emptyList()
var teachers = all.groupBy { it.teacher }
var group = all.groupBy { it.group }
var discipline = all.groupBy { it.discipline }

fun importExcel (importFile: String): Boolean {
    print("ghghghgh")
    val filepath = FileInputStream("F:\\$importFile")
    val excelTable = WorkbookFactory.create(filepath)
    val tables = excelTable.getSheetAt(0)
     all = readFromExcelFile(tables)
     teachers = all.groupBy { it.teacher }
     group = all.groupBy { it.group }
     discipline = all.groupBy { it.discipline }
    return all != emptyList<Inform>()
}
fun readFromExcelFile(tables: Sheet):  List<Inform> {
    val import = mutableListOf<Inform>()
    var rowNumber = 3 // строка
    val finish = 138

    var columnNumber = 0 // столбец
    val finishCall =11


    while(rowNumber< finish){

        while(columnNumber<finishCall){
            val number = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val plan = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val focus = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val block = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val discipline = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val semestr = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val group = tables.getRow(rowNumber).getCell(columnNumber).toString()
            val g1 = group.substringBefore("Основной")
            columnNumber++
            val kol = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val vid = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val time = tables.getRow(rowNumber).getCell(columnNumber).toString()
            columnNumber++
            val teacher = tables.getRow(rowNumber).getCell(columnNumber).toString()
            val t1 = teacher.substringAfter(" ")
            val t2 = t1.substringAfter(" ")


            columnNumber++
            val zan = tables.getRow(rowNumber).getCell(columnNumber).toString()

            import.add(Inform(
                number.substringBefore(" "),
                plan.substringBefore(","),
                focus.substringBefore(","),
                block.substringBefore(","),
                Discipline(
                    discipline.substringAfter("(")
                ),
                semestr.substringBefore(" "),
                Group(
                    g1.substringBefore(" "),
                    g1.substringAfter(" ")
                ),
                kol.substringBefore(" "),
                vid.substringBefore(","),
                time.substringBefore(","),
                Teacher(
                    teacher.substringBefore(" "),
                    t1.substringBefore(" "),
                    t2.substringBefore("(")
                ),
                zan.substringBefore(",")
            ))
        }
        rowNumber++
        columnNumber=0
    }
    return import
}
/*
val all = readFromExcelFile(tables)
val teachers = all.groupBy { it.teacher }
val group = all.groupBy { it.group }
val discipline = all.groupBy { it.discipline }*/

