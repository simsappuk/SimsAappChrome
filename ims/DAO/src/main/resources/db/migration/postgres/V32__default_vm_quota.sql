update registry_account set vm_quota = 20 where vm_quota is null  and account_type <> 'DOCKER_REGISTRY';
