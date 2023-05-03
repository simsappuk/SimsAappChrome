-- System Settings updates

update app_config set value_ = 'https://repo.skygrid.cloud/repo/change-to-version/agents/hcp_linux_agent.sh' where id = 'dchq.agent.script.url';
update app_config set value_ = 'https://repo.skygrid.cloud/repo/change-to-version/agents/hcp_agent_install_windows.ps1' where id = 'dchq.win.agent.script.url';
