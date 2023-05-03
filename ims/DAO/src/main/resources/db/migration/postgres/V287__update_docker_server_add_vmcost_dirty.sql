ALTER TABLE docker_server add COLUMN vm_cost_hr NUMERIC (5, 2);
ALTER TABLE docker_server add COLUMN dirty_cost BOOLEAN DEFAULT TRUE;