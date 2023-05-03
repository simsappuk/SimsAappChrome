-- System Settings updates

update app_config set value_ = '5671' where id = 'dchq.agent.connect.port';
update app_config set value_ = 'dchq.io' where id = 'dchq.agent.connect.ip';