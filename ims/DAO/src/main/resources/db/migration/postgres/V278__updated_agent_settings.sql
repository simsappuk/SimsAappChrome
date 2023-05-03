-- System Settings updates

update app_config set value_ = 'https://change-to-hcp-hostname' where id = 'dchq.base.url';
update app_config set value_ = 'change-to-hcp-hostname' where id = 'dchq.agent.connect.ip';
update app_config set value_ = 'https://repo.skygrid.cloud/repo/agents/hcp_linux_agent.sh' where id = 'dchq.agent.script.url';
update app_config set value_ = 'https://repo.skygrid.cloud/repo/agents/hcp_agent_install_windows.ps1' where id = 'dchq.win.agent.script.url';

delete from app_config where id = 'dchq.master';
delete from app_config where id = 'dchq.node';