create table tasks_bak as select * from tasks where 0=1;                               
create table incidents_bak   as select * from incidents   where 0=1;                   
create table t_subprocess_bak   as select * from t_subprocess   where 0=1;             
create table t_approvedinfo_bak   as select * from t_approvedinfo  where 0=1;          
create table t_doc_send_bak   as select * from t_doc_send where 0=1;                   
create table t_doc_receive_bak   as select * from t_doc_receive where 0=1;             
create table t_job_contact_bak   as select * from t_job_contact where 0=1;             
create table ht_xx_bak   as select * from ht_xx  where 0=1;                            
create table pcl_project_basic_info_bak   as select * from pcl_project_basic_info where
create table t_doc_db_bak   as select * from t_doc_db where 0=1;                       
create table t_receive_directive_bak   as select * from t_receive_directive  where 0=1;

CREATE TABLE T_PROCESS_DONE
(
  ID       					VARCHAR2(50)                  DEFAULT SYS_GUID()            NOT NULL,
  PID      					VARCHAR2(50),
  PNAME 		 				VARCHAR2(50),
  PTYPE      				VARCHAR2(50),  
  SUMMARY  					VARCHAR2(2000),  
  START_TIME  				DATE,
  END_TIME  					DATE,
  APPLY_USER     		VARCHAR2(25),  
  APPLY_DEPT        VARCHAR2(50),  
  DONE_USERS      	VARCHAR2(4000),
  STATUS            INTEGER                             DEFAULT 0,
  DATA           		CLOB  ,
  REMARK				VARCHAR2(200),
  CONSTRAINT PK_T_PROCESS_DONE PRIMARY KEY (ID)
);


COMMENT ON TABLE T_PROCESS_DONE IS '已办流程信息表';

COMMENT ON COLUMN T_PROCESS_DONE.ID IS '系统主键';

COMMENT ON COLUMN T_PROCESS_DONE.PID IS '流程ID';

COMMENT ON COLUMN T_PROCESS_DONE.PNAME IS '流程名称，中文标识';

COMMENT ON COLUMN T_PROCESS_DONE.PTYPE IS '流程类型,英文标识';

COMMENT ON COLUMN T_PROCESS_DONE.SUMMARY IS '摘要';

COMMENT ON COLUMN T_PROCESS_DONE.START_TIME IS '开始时间';

COMMENT ON COLUMN T_PROCESS_DONE.END_TIME IS '完成时间';

COMMENT ON COLUMN T_PROCESS_DONE.APPLY_USER IS '发起人姓名';

COMMENT ON COLUMN T_PROCESS_DONE.APPLY_DEPT IS '发起部门名称';

COMMENT ON COLUMN T_PROCESS_DONE.DONE_USERS IS '已办理人工号,以逗号分隔的多个用户工号（16位带部门ID）';

COMMENT ON COLUMN T_PROCESS_DONE.STATUS IS '完成状态（1:进行中,2：预归档,3:已归档,33:手动删除）';

COMMENT ON COLUMN T_PROCESS_DONE.DATA IS '业务数据';

COMMENT ON COLUMN T_PROCESS_DONE.REMARK IS '备注';

CREATE INDEX IDX_T_PROCESS_DONE_PID_PNAME ON T_PROCESS_DONE
(PID,PNAME);

alter table T_PROCESS_DONE add TASKID VARCHAR2(64);
COMMENT ON COLUMN T_PROCESS_DONE.TASKID IS 'tasks表主键';

update t_process_done t set t.taskid = (select a.taskid from tasks_bak a where a.steplabel='Begin' and a.incident=t.pid and a.processName=t.pname)




