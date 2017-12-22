package repositories

import scalikejdbc.config.DBs

trait DbSettings {
  DBs.setupAll()
}
