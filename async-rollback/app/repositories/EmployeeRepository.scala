package repositories

import models.Employee
import scalikejdbc.DBSession

import scala.concurrent.Future

trait EmployeeRepository {

  def findAll()(implicit session: DBSession): Future[List[Employee]]
  def create(employee: Employee)(implicit session: DBSession): Future[Employee]

}
