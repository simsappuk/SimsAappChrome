-- default system settings


insert into app_config (id, key_, value_, description) values ('dchq.agent.script.url', 'dchq.agent.script.url', 'https://www.dropbox.com/s/fxys4nyhy7t209h/dchq_agent_install_v2.sh?dl=1', 'DCHQ agent location');
insert into app_config (id, key_, value_, description) values ('dchq.agent.connect.ip', 'dchq.agent.connect.ip', '67.188.92.120', 'DCHQ RabbitMQ ip/hostname');
insert into app_config (id, key_, value_, description) values ('dchq.agent.connect.port', 'dchq.agent.connect.port', '32777', 'DCHQ RabbitMQ port');
insert into app_config (id, key_, value_, description) values ('dchq.base.url', 'dchq.base.url', 'http://67.188.92.120:9090', 'DCHQ base url, this is used in notification and emails e.g. https://dchq.io');