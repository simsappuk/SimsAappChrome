-- System Settings updates

update app_config set value_ = '5671' where id = 'dchq.agent.connect.ip';
update app_config set value_ = 'HyperForm' where id = 'dchq.title';
update app_config set value_ = 'https://www.dropbox.com/s/i0tjav1yftfrskq/dchq_agent_install_v10.2_ssl.sh?dl=1' where id = 'dchq.agent.script.url';