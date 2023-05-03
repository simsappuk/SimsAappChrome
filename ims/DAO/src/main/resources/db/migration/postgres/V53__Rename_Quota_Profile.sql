-- Rename Quota_Profile to Quota_Policy


alter table quota_profile rename to quota_policy;

alter table registry_account_quota_profile_pks rename to registry_account_quota_policy_pks;

alter table registry_account_quota_profiles rename to registry_account_quota_policies;

alter table registry_account_quota_policy_pks rename column quota_profile_pks to quota_policy_pks;

alter table registry_account_quota_policies rename column quota_profiles_id to quota_policies_id;
