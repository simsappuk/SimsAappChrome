-- Column added for Swarm service mode

alter table container add column node_name character varying(255);
