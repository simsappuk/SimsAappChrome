-- Table: update table docker_server to add schedule start/stop VM functionality.

ALTER TABLE docker_server ADD COLUMN schedule_start_time timestamp without time zone;
ALTER TABLE docker_server ADD COLUMN schedule_stop_time timestamp without time zone;
ALTER TABLE docker_server ADD COLUMN scheduled boolean DEFAULT false;


COMMENT ON COLUMN docker_server.schedule_start_time is 'Next time when this VM will schedule to be started';
COMMENT ON COLUMN docker_server.schedule_stop_time is 'Next time when this VM will schedule to be stopped';
COMMENT ON COLUMN docker_server.scheduled is 'if true then VM is a scheduled VM';

-- Create table docker_server_schedule to add vm start/stop schedules
CREATE TABLE docker_server_schedule
(
  id character varying(255) NOT NULL,
  docker_server_id character varying(255) NOT NULL,
  created_by character varying(255),
  created_date timestamp without time zone,
  last_modified_by character varying(255),
  last_modified_date timestamp without time zone,
  start_schedule character varying(255),
  start_date timestamp without time zone,
  start_frequency character varying(255),
  end_date timestamp without time zone,
  stop_duration character varying(255),
  display_option character varying(255) NOT NULL,
  active boolean,
  CONSTRAINT schedule_id_pk PRIMARY KEY (id),
  CONSTRAINT docker_server_id_fk FOREIGN KEY (docker_server_id)
        REFERENCES docker_server (id)
);

COMMENT ON TABLE  docker_server_schedule is 'Table to store VM start and stop schedules, It can be recurrence or one time';

COMMENT ON COLUMN docker_server_schedule.id is 'Unique id attached to a schedule';
COMMENT ON COLUMN docker_server_schedule.docker_server_id is 'docker server id for which this schedule is attched is actual foreign key';
COMMENT ON COLUMN docker_server_schedule.start_schedule is 'Contain the cron expression for start VM schedule in case of recurrence';
COMMENT ON COLUMN docker_server_schedule.start_date is 'StartDate of schedule in case of recurrence else store one time startDate';
COMMENT ON COLUMN docker_server_schedule.start_frequency is 'Frequency of start_schedule .Used for option 2';
COMMENT ON COLUMN docker_server_schedule.end_date is 'EndDate of schedule in case of recurrence else store one time stopDate';
COMMENT ON COLUMN docker_server_schedule.stop_duration is 'Stores duration after which VM stop event will be triggered';
COMMENT ON COLUMN docker_server_schedule.display_option is 'Stores the display format of UI #1:Recurrence with Controls #2:No recurrence with specific Dates #3:Cron Expression';
COMMENT ON COLUMN docker_server_schedule.active is 'Stores if schedule is enable or disable';
