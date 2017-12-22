package infras

import javax.inject.Singleton

import models.Employee
import repositories.EmployeeRepository
import scalikejdbc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class EmployeeRepositoryImpl extends EmployeeRepository {

  private val e = Employee.e
  private val c = Employee.c

  override def findAll()(implicit session: DBSession = AutoSession): Future[List[Employee]] = Future {
    doFindAll()
  }

  override def create(employee: Employee)(implicit session: DBSession): Future[Employee] = Future {
    doCreate(employee)
  }

  private def doFindAll()(implicit session: DBSession): List[Employee] = withSQL {
    select
      .from(Employee as e)
  }.map(Employee(e)).list().apply()

  private def doCreate(employee: Employee)(implicit session: DBSession): Employee = {
    val id = withSQL {
      insert.into(Employee).namedValues(
        c.name -> employee.name
      )
    }.updateAndReturnGeneratedKey().apply()

    Employee(Some(id), employee.name)
  }

}
