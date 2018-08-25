任务持久化需要用到数据库，而初始化数据库的SQL可以从下载的发布版的文件中找到，比如，我在官网的Download页下载了当前版本的Full Distribution：Quartz 2.2.3 .tar.gz，解压后在quartz-2.2.3\docs\dbTables能找到初始化脚本，因我用的是MySQL的Innodb引擎，所以我用此脚本tables_mysql_innodb.sql。

删除数据SQL（删除数据主要用于调试）：

delete from qrtz_fired_triggers;
delete from qrtz_paused_trigger_grps;
delete from qrtz_scheduler_state;
delete from qrtz_locks;
delete from qrtz_simple_triggers;
delete from qrtz_simprop_triggers;
delete from qrtz_cron_triggers;
delete from qrtz_blob_triggers;
delete from qrtz_triggers;
delete from qrtz_job_details;
delete from qrtz_calendars;