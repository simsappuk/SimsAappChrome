-- @Author Mohammed Luqman Shareef
-- Agent Install Script - For Windows

alter table docker_server add column install_script_windows text;


insert into app_config (id, key_, value_, description) values ('dchq.win.agent.script.url', 'dchq.win.agent.script.url', 'https://www.dropbox.com/s/r5ofld3xme74z20/DCHQ_Agent_Install_Windows.ps1?dl=1', 'DCHQ Agent for Windows');
