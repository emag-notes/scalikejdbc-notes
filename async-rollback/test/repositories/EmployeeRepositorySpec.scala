package repositories

import infras.EmployeeRepositoryImpl
import models.Employee
import org.scalatest.fixture
import scalikejdbc.scalatest.AsyncAutoRollback
import scalikejdbc._

class EmployeeRepositorySpec
  extends fixture.AsyncFlatSpec
    with AsyncAutoRollback
    with DbSettings {

  "findAll" should "Employee リストを取得する" in { implicit s =>
    val repository: EmployeeRepository = new EmployeeRepositoryImpl
    val futureEmployees = repository.findAll()
    futureEmployees.map { employees => {
      assert(employees.size === 2)
    }}
  }

  "create" should "Employee を登録する" in { implicit s =>
    val repository = new EmployeeRepositoryImpl
    val futureNewEmployee = repository.create(Employee(name = "Joe"))
    futureNewEmployee.map { employee => {
      assert(employee.name == "Joe")
    }}
  }

  override def fixture(implicit session: FixtureParam): Unit = {
    sql"INSERT INTO employees (name) VALUES ('Jane')".update().apply()
  }

}
