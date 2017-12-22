package models

import scalikejdbc._

case class Employee(id: Option[Long] = None, name: String)

object Employee extends SQLSyntaxSupport[Employee] {

  override val tableName = "employees"

  val e = syntax("e")
  val c = column

  def apply(e: ResultName[Employee])(rs: WrappedResultSet): Employee =
    Employee(id = rs.get(e.id), name = rs.get(e.name))

  def apply(e: SyntaxProvider[Employee])(rs: WrappedResultSet): Employee =
    Employee(e.resultName)(rs)

}