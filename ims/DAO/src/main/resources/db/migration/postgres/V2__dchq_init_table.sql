--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-09-14 14:19:45

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 296 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 296
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 140746)
-- Name: agent_ping; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE agent_ping (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    key_ character varying(255),
    last_ping timestamp without time zone
);


ALTER TABLE agent_ping OWNER TO dchq;

--
-- TOC entry 185 (class 1259 OID 140754)
-- Name: app_backup_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_backup_profiles (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    note text,
    provision character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_backup_profiles OWNER TO dchq;

--
-- TOC entry 186 (class 1259 OID 140762)
-- Name: app_backup_profiles_containers; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_backup_profiles_containers (
    app_backup_profiles_id character varying(255) NOT NULL,
    containers_id character varying(255) NOT NULL
);


ALTER TABLE app_backup_profiles_containers OWNER TO dchq;

--
-- TOC entry 187 (class 1259 OID 140768)
-- Name: app_backups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_backups (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    note text,
    provision character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_backups OWNER TO dchq;

--
-- TOC entry 188 (class 1259 OID 140776)
-- Name: app_backups_containers; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_backups_containers (
    app_backedup_info_id character varying(255) NOT NULL,
    containers_id character varying(255) NOT NULL
);


ALTER TABLE app_backups_containers OWNER TO dchq;

--
-- TOC entry 294 (class 1259 OID 142446)
-- Name: app_config; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_config (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    description character varying(255),
    key_ character varying(255),
    value_ character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_config OWNER TO dchq;

--
-- TOC entry 189 (class 1259 OID 140782)
-- Name: app_plugin_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_plugin_profiles (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    continuous boolean,
    note text,
    provision character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_plugin_profiles OWNER TO dchq;

--
-- TOC entry 190 (class 1259 OID 140790)
-- Name: app_plugin_profiles_containers; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_plugin_profiles_containers (
    app_plugin_profiles_id character varying(255) NOT NULL,
    containers_id character varying(255) NOT NULL
);


ALTER TABLE app_plugin_profiles_containers OWNER TO dchq;

--
-- TOC entry 191 (class 1259 OID 140796)
-- Name: app_rollback_profile; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_rollback_profile (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    note text,
    provision character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_rollback_profile OWNER TO dchq;

--
-- TOC entry 192 (class 1259 OID 140804)
-- Name: app_rollback_profile_container; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_rollback_profile_container (
    app_rollback_profile_id character varying(255) NOT NULL,
    active boolean,
    container_id character varying(255),
    container_name character varying(255),
    registry_id character varying(255),
    repo character varying(255),
    tag character varying(255)
);


ALTER TABLE app_rollback_profile_container OWNER TO dchq;

--
-- TOC entry 193 (class 1259 OID 140810)
-- Name: app_scale_in_profile_cluster_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_scale_in_profile_cluster_profiles (
    app_scale_in_profile_id character varying(255) NOT NULL,
    active boolean,
    cluster_name character varying(255),
    cron_expression character varying(255),
    new_active integer,
    total_active integer
);


ALTER TABLE app_scale_in_profile_cluster_profiles OWNER TO dchq;

--
-- TOC entry 194 (class 1259 OID 140816)
-- Name: app_scale_in_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_scale_in_profiles (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    next_cron_date timestamp without time zone,
    note text,
    provision character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_scale_in_profiles OWNER TO dchq;

--
-- TOC entry 195 (class 1259 OID 140824)
-- Name: app_scale_out_profile_cluster_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_scale_out_profile_cluster_profiles (
    app_scale_out_profile_id character varying(255) NOT NULL,
    active boolean,
    cluster_name character varying(255),
    cron_expression character varying(255),
    new_active integer,
    total_active integer
);


ALTER TABLE app_scale_out_profile_cluster_profiles OWNER TO dchq;

--
-- TOC entry 196 (class 1259 OID 140830)
-- Name: app_scale_out_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE app_scale_out_profiles (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    next_cron_date timestamp without time zone,
    note text,
    provision character varying(255),
    owner_id character varying(255)
);


ALTER TABLE app_scale_out_profiles OWNER TO dchq;

--
-- TOC entry 197 (class 1259 OID 140838)
-- Name: blueprint; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    blueprint_type character varying(255),
    description text,
    git_checksum character varying(255),
    git_id character varying(255),
    git_public_visible boolean,
    git_url character varying(255),
    name character varying(255),
    total_run integer,
    total_stars integer,
    user_name character varying(255),
    version character varying(255),
    yml text,
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL,
    image_id character varying(255)
);


ALTER TABLE blueprint OWNER TO dchq;

--
-- TOC entry 198 (class 1259 OID 140846)
-- Name: blueprint_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint_entitled_groups_pks (
    blueprint_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE blueprint_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 199 (class 1259 OID 140852)
-- Name: blueprint_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint_entitled_orgs (
    blueprint_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE blueprint_entitled_orgs OWNER TO dchq;

--
-- TOC entry 200 (class 1259 OID 140860)
-- Name: blueprint_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint_entitled_user_groups (
    blueprint_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE blueprint_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 201 (class 1259 OID 140868)
-- Name: blueprint_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint_entitled_users (
    blueprint_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE blueprint_entitled_users OWNER TO dchq;

--
-- TOC entry 202 (class 1259 OID 140876)
-- Name: blueprint_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint_entitled_users_pks (
    blueprint_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE blueprint_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 203 (class 1259 OID 140882)
-- Name: blueprint_images; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE blueprint_images (
    blueprint_id character varying(255) NOT NULL,
    images_id character varying(255) NOT NULL
);


ALTER TABLE blueprint_images OWNER TO dchq;

--
-- TOC entry 204 (class 1259 OID 140888)
-- Name: build; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    build_task_status character varying(255),
    build_type character varying(255),
    cluster character varying(255),
    description text,
    docker_script text,
    git_checksum character varying(255),
    git_id character varying(255),
    git_public_visible boolean,
    git_url character varying(255),
    git_branch character varying(255),
    git_clone_url character varying(255),
    git_credentials character varying(255),
    last_build_duration bigint NOT NULL,
    last_failed_build_number integer,
    last_failure timestamp without time zone,
    last_success timestamp without time zone,
    last_success_build_number integer,
    most_recent_build_number integer,
    next_cron_date timestamp without time zone,
    repository character varying(255),
    server_id character varying(255),
    tag character varying(255),
    "time" character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL,
    registry_account_id character varying(255)
);


ALTER TABLE build OWNER TO dchq;

--
-- TOC entry 205 (class 1259 OID 140896)
-- Name: build_build_tasks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_build_tasks (
    build_id character varying(255) NOT NULL,
    build_tasks_id character varying(255) NOT NULL
);


ALTER TABLE build_build_tasks OWNER TO dchq;

--
-- TOC entry 206 (class 1259 OID 140902)
-- Name: build_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_entitled_groups_pks (
    build_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE build_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 207 (class 1259 OID 140908)
-- Name: build_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_entitled_orgs (
    build_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE build_entitled_orgs OWNER TO dchq;

--
-- TOC entry 208 (class 1259 OID 140916)
-- Name: build_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_entitled_user_groups (
    build_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE build_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 209 (class 1259 OID 140924)
-- Name: build_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_entitled_users (
    build_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE build_entitled_users OWNER TO dchq;

--
-- TOC entry 210 (class 1259 OID 140932)
-- Name: build_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_entitled_users_pks (
    build_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE build_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 211 (class 1259 OID 140938)
-- Name: build_task; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE build_task (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    build_duration bigint NOT NULL,
    build_id character varying(255),
    build_number integer,
    build_task_status character varying(255),
    build_type character varying(255),
    docker_file_content text,
    git_branch character varying(255),
    git_clone_url character varying(255),
    log text,
    registry_account character varying(255),
    repository character varying(255),
    server_id character varying(255),
    tag character varying(255),
    owner_id character varying(255)
);


ALTER TABLE build_task OWNER TO dchq;

--
-- TOC entry 212 (class 1259 OID 140946)
-- Name: certified_image; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE certified_image (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    certified_image_status character varying(255),
    image_id character varying(255),
    image_name character varying(255),
    provider character varying(255),
    region character varying(255),
    owner_id character varying(255)
);


ALTER TABLE certified_image OWNER TO dchq;

--
-- TOC entry 213 (class 1259 OID 140954)
-- Name: container; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    cluster_name character varying(255),
    clusterable boolean,
    container_expected_status character varying(255),
    container_id character varying(255),
    container_name character varying(255),
    bridge character varying(255),
    gateway character varying(255),
    ip_address character varying(255),
    exit_code integer,
    finished_at character varying(255),
    paused boolean,
    pid integer,
    running boolean,
    started_at character varying(255),
    state character varying(255),
    container_status character varying(255),
    cpu_shares integer,
    domainname character varying(255),
    entrypoint character varying(255),
    expose_ports boolean,
    host character varying(255),
    hostname character varying(255),
    image character varying(255),
    image_id character varying(255),
    image_pk character varying(255),
    log text,
    memory_limits bigint,
    memory_min bigint,
    name character varying(255),
    net character varying(255),
    pid_mode character varying(255),
    privileged character varying(255),
    cpu_perc double precision,
    proc_cpu_sys character varying(255),
    proc_cpu_total character varying(255),
    proc_kernel_priority character varying(255),
    threads double precision,
    proc_mem_page_faults character varying(255),
    proc_mem_resident character varying(255),
    proc_mem_shared character varying(255),
    proc_mem_virtual character varying(255),
    registry_id character varying(255),
    restart character varying(255),
    rx double precision,
    status character varying(255),
    stdin_open boolean,
    tty boolean,
    tx double precision,
    up_time character varying(255),
    user_ character varying(255),
    working_dir character varying(255),
    owner_id character varying(255),
    docker_server_id character varying(255),
    provision_id character varying(255)
);


ALTER TABLE container OWNER TO dchq;

--
-- TOC entry 214 (class 1259 OID 140962)
-- Name: container_backup_profile; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_backup_profile (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    active boolean,
    container_id character varying(255),
    container_name character varying(255),
    next_cron_date timestamp without time zone,
    registry_id character varying(255),
    repo character varying(255),
    tag character varying(255),
    time_ character varying(255),
    owner_id character varying(255)
);


ALTER TABLE container_backup_profile OWNER TO dchq;

--
-- TOC entry 215 (class 1259 OID 140970)
-- Name: container_cap_add; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_cap_add (
    container_id character varying(255) NOT NULL,
    cap_add character varying(255)
);


ALTER TABLE container_cap_add OWNER TO dchq;

--
-- TOC entry 216 (class 1259 OID 140976)
-- Name: container_cap_drop; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_cap_drop (
    container_id character varying(255) NOT NULL,
    cap_drop character varying(255)
);


ALTER TABLE container_cap_drop OWNER TO dchq;

--
-- TOC entry 217 (class 1259 OID 140982)
-- Name: container_commands; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_commands (
    container_id character varying(255) NOT NULL,
    commands character varying(255)
);


ALTER TABLE container_commands OWNER TO dchq;

--
-- TOC entry 218 (class 1259 OID 140988)
-- Name: container_dependencies; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_dependencies (
    container_id character varying(255) NOT NULL,
    dependencies character varying(255),
    dependencies_key character varying(255) NOT NULL
);


ALTER TABLE container_dependencies OWNER TO dchq;

--
-- TOC entry 219 (class 1259 OID 140996)
-- Name: container_dns; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_dns (
    container_id character varying(255) NOT NULL,
    dns character varying(255)
);


ALTER TABLE container_dns OWNER TO dchq;

--
-- TOC entry 220 (class 1259 OID 141002)
-- Name: container_dns_search; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_dns_search (
    container_id character varying(255) NOT NULL,
    dns_search character varying(255)
);


ALTER TABLE container_dns_search OWNER TO dchq;

--
-- TOC entry 221 (class 1259 OID 141008)
-- Name: container_envs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_envs (
    container_id character varying(255) NOT NULL,
    eval character varying(255),
    hidden boolean,
    prop character varying(255),
    val character varying(255)
);


ALTER TABLE container_envs OWNER TO dchq;

--
-- TOC entry 222 (class 1259 OID 141014)
-- Name: container_monitor_stat; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_monitor_stat (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    container_id character varying(255),
    dt timestamp without time zone,
    cpu_perc double precision,
    proc_kernel_threads double precision,
    mem_perc double precision,
    rx double precision,
    tx double precision
);


ALTER TABLE container_monitor_stat OWNER TO dchq;

--
-- TOC entry 223 (class 1259 OID 141022)
-- Name: container_plugin_profile; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_plugin_profile (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    active boolean,
    build_no character varying(255),
    container_id character varying(255),
    container_name character varying(255),
    endpoint character varying(255),
    jobname character varying(255),
    next_cron_date timestamp without time zone,
    time_ character varying(255),
    owner_id character varying(255)
);


ALTER TABLE container_plugin_profile OWNER TO dchq;

--
-- TOC entry 224 (class 1259 OID 141030)
-- Name: container_plugin_profile_plugin_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_plugin_profile_plugin_profiles (
    container_plugin_profile_id character varying(255) NOT NULL,
    plugin_profiles_id character varying(255) NOT NULL
);


ALTER TABLE container_plugin_profile_plugin_profiles OWNER TO dchq;

--
-- TOC entry 225 (class 1259 OID 141036)
-- Name: container_plugin_profiles; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_plugin_profiles (
    container_id character varying(255) NOT NULL,
    plugin_profiles_id character varying(255) NOT NULL
);


ALTER TABLE container_plugin_profiles OWNER TO dchq;

--
-- TOC entry 226 (class 1259 OID 141042)
-- Name: container_port_bindings; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_port_bindings (
    container_id character varying(255) NOT NULL,
    port_bindings character varying(255)
);


ALTER TABLE container_port_bindings OWNER TO dchq;

--
-- TOC entry 227 (class 1259 OID 141048)
-- Name: container_volumes; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE container_volumes (
    container_id character varying(255) NOT NULL,
    volumes character varying(255)
);


ALTER TABLE container_volumes OWNER TO dchq;

--
-- TOC entry 228 (class 1259 OID 141054)
-- Name: data_center; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE data_center (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    auto_scale boolean,
    data_center_pod character varying(255),
    description text,
    endpoint character varying(255),
    name character varying(255),
    network character varying(255),
    network_pass character varying(255),
    placement_policy character varying(255),
    profile_enforced boolean,
    scale_limit integer,
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE data_center OWNER TO dchq;

--
-- TOC entry 229 (class 1259 OID 141062)
-- Name: data_center_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE data_center_entitled_groups_pks (
    data_center_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE data_center_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 230 (class 1259 OID 141068)
-- Name: data_center_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE data_center_entitled_orgs (
    data_center_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE data_center_entitled_orgs OWNER TO dchq;

--
-- TOC entry 231 (class 1259 OID 141076)
-- Name: data_center_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE data_center_entitled_user_groups (
    data_center_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE data_center_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 232 (class 1259 OID 141084)
-- Name: data_center_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE data_center_entitled_users (
    data_center_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE data_center_entitled_users OWNER TO dchq;

--
-- TOC entry 233 (class 1259 OID 141092)
-- Name: data_center_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE data_center_entitled_users_pks (
    data_center_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE data_center_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 234 (class 1259 OID 141098)
-- Name: docker_server; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE docker_server (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    data_center_name character varying(255),
    description character varying(255),
    docker_server_status character varying(255),
    endpoint character varying(255),
    endpoint_type character varying(255),
    error_logs text,
    hardware_id character varying(255),
    hardware_name character varying(255),
    host_or_ip character varying(255),
    image_id character varying(255),
    image_name character varying(255),
    image_password character varying(255),
    image_username character varying(255),
    inbound_ports character varying(255),
    install_script text,
    key_pair_id character varying(255),
    key_pair_name character varying(255),
    max_port_number integer,
    max_port_number_in_use integer,
    min_port_number integer,
    name character varying(255),
    network_id character varying(255),
    network_name character varying(255),
    node_id character varying(255),
    region character varying(255),
    region_name character varying(255),
    routing_key character varying(255),
    security_group_id character varying(255),
    security_group_name character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL,
    data_center_id character varying(255)
);


ALTER TABLE docker_server OWNER TO dchq;

--
-- TOC entry 235 (class 1259 OID 141106)
-- Name: docker_server_envs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE docker_server_envs (
    docker_server_id character varying(255) NOT NULL,
    envs character varying(255)
);


ALTER TABLE docker_server_envs OWNER TO dchq;

--
-- TOC entry 236 (class 1259 OID 141112)
-- Name: docker_server_image; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE docker_server_image (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    docker_image character varying(255),
    docker_server_image_status character varying(255),
    error_log text,
    owner_id character varying(255),
    docker_server_id character varying(255),
    image_id character varying(255),
    registry_account_id character varying(255)
);


ALTER TABLE docker_server_image OWNER TO dchq;

--
-- TOC entry 237 (class 1259 OID 141120)
-- Name: docker_server_task; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE docker_server_task (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    docker_server_task_status character varying(255),
    docker_server_task_type character varying(255),
    ended timestamp without time zone,
    error_msg text,
    note text,
    retry_attempts integer,
    scheduled timestamp without time zone,
    started timestamp without time zone,
    waiting_on character varying(255),
    owner_id character varying(255),
    docker_server_id character varying(255)
);


ALTER TABLE docker_server_task OWNER TO dchq;

--
-- TOC entry 238 (class 1259 OID 141128)
-- Name: email_alert; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE email_alert (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    dt date,
    entity_id character varying(255),
    entity_type character varying(255),
    message_resolution character varying(255),
    secondary_id character varying(255),
    to_user character varying(255)
);


ALTER TABLE email_alert OWNER TO dchq;

--
-- TOC entry 239 (class 1259 OID 141136)
-- Name: image; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    cluster_size integer,
    clusterable boolean,
    cpu integer,
    description text,
    docker_image character varying(255),
    expose_ports boolean,
    image_url character varying(255),
    memory bigint,
    name character varying(255),
    tags character varying(255),
    owner_id character varying(255),
    registry_account_id character varying(255)
);


ALTER TABLE image OWNER TO dchq;

--
-- TOC entry 240 (class 1259 OID 141144)
-- Name: image_commands; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image_commands (
    image_id character varying(255) NOT NULL,
    commands character varying(255)
);


ALTER TABLE image_commands OWNER TO dchq;

--
-- TOC entry 241 (class 1259 OID 141150)
-- Name: image_envs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image_envs (
    image_id character varying(255) NOT NULL,
    eval character varying(255),
    hidden boolean,
    prop character varying(255),
    val character varying(255)
);


ALTER TABLE image_envs OWNER TO dchq;

--
-- TOC entry 242 (class 1259 OID 141156)
-- Name: image_plugins; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image_plugins (
    image_id character varying(255) NOT NULL,
    plugins_id character varying(255) NOT NULL
);


ALTER TABLE image_plugins OWNER TO dchq;

--
-- TOC entry 243 (class 1259 OID 141164)
-- Name: image_port_bindings; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image_port_bindings (
    image_id character varying(255) NOT NULL,
    port_bindings character varying(255)
);


ALTER TABLE image_port_bindings OWNER TO dchq;

--
-- TOC entry 244 (class 1259 OID 141170)
-- Name: image_ports; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image_ports (
    image_id character varying(255) NOT NULL,
    port integer,
    protocol character varying(255)
);


ALTER TABLE image_ports OWNER TO dchq;

--
-- TOC entry 245 (class 1259 OID 141176)
-- Name: image_volumes; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE image_volumes (
    image_id character varying(255) NOT NULL,
    volumes character varying(255)
);


ALTER TABLE image_volumes OWNER TO dchq;

--
-- TOC entry 246 (class 1259 OID 141182)
-- Name: inbox_message; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE inbox_message (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    body text,
    dt timestamp without time zone,
    entity_id character varying(255),
    entity_type character varying(255),
    extended_notification character varying(255),
    extended_notification_date timestamp without time zone,
    from_user character varying(255),
    message_resolution character varying(255),
    message_severity character varying(255),
    message_status character varying(255),
    message_type character varying(255),
    resolution_dt timestamp without time zone,
    resolution_text text,
    secondary_id character varying(255),
    subject text,
    to_user character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE inbox_message OWNER TO dchq;

--
-- TOC entry 247 (class 1259 OID 141190)
-- Name: inbox_message_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE inbox_message_entitled_orgs (
    inbox_message_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE inbox_message_entitled_orgs OWNER TO dchq;

--
-- TOC entry 248 (class 1259 OID 141198)
-- Name: inbox_message_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE inbox_message_entitled_user_groups (
    inbox_message_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE inbox_message_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 249 (class 1259 OID 141206)
-- Name: inbox_message_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE inbox_message_entitled_users (
    inbox_message_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE inbox_message_entitled_users OWNER TO dchq;

--
-- TOC entry 250 (class 1259 OID 141214)
-- Name: message_action_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE message_action_users (
    message_id character varying(255) NOT NULL,
    action_users character varying(255)
);


ALTER TABLE message_action_users OWNER TO dchq;

--
-- TOC entry 251 (class 1259 OID 141220)
-- Name: message_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE message_entitled_groups_pks (
    message_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE message_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 252 (class 1259 OID 141226)
-- Name: message_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE message_entitled_users_pks (
    message_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE message_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 253 (class 1259 OID 141232)
-- Name: organization; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE organization (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    name character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE organization OWNER TO dchq;

--
-- TOC entry 254 (class 1259 OID 141240)
-- Name: password_reset_token; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE password_reset_token (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    expiration_date timestamp without time zone,
    token character varying(255),
    used boolean,
    user_pk character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE password_reset_token OWNER TO dchq;

--
-- TOC entry 255 (class 1259 OID 141248)
-- Name: plugin; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    base_script text,
    description text,
    git_checksum character varying(255),
    git_id character varying(255),
    git_public_visible boolean,
    git_url character varying(255),
    license character varying(255),
    name character varying(255),
    post_script text,
    reference_id character varying(255),
    restart boolean,
    total_stars integer,
    version character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE plugin OWNER TO dchq;

--
-- TOC entry 256 (class 1259 OID 141256)
-- Name: plugin_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_entitled_groups_pks (
    plugin_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE plugin_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 257 (class 1259 OID 141262)
-- Name: plugin_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_entitled_orgs (
    plugin_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE plugin_entitled_orgs OWNER TO dchq;

--
-- TOC entry 258 (class 1259 OID 141270)
-- Name: plugin_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_entitled_user_groups (
    plugin_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE plugin_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 259 (class 1259 OID 141278)
-- Name: plugin_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_entitled_users (
    plugin_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE plugin_entitled_users OWNER TO dchq;

--
-- TOC entry 260 (class 1259 OID 141286)
-- Name: plugin_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_entitled_users_pks (
    plugin_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE plugin_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 261 (class 1259 OID 141292)
-- Name: plugin_envs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_envs (
    plugin_id character varying(255) NOT NULL,
    eval character varying(255),
    hidden boolean,
    prop character varying(255),
    val character varying(255)
);


ALTER TABLE plugin_envs OWNER TO dchq;

--
-- TOC entry 262 (class 1259 OID 141298)
-- Name: plugin_profile; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_profile (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    name character varying(255),
    plugin_id character varying(255),
    restart boolean,
    owner_id character varying(255)
);


ALTER TABLE plugin_profile OWNER TO dchq;

--
-- TOC entry 263 (class 1259 OID 141306)
-- Name: plugin_profile_envs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_profile_envs (
    plugin_profile_id character varying(255) NOT NULL,
    eval character varying(255),
    hidden boolean,
    prop character varying(255),
    val character varying(255)
);


ALTER TABLE plugin_profile_envs OWNER TO dchq;

--
-- TOC entry 264 (class 1259 OID 141312)
-- Name: plugin_star; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE plugin_star (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    dt timestamp without time zone,
    plugin_id character varying(255),
    owner_id character varying(255)
);


ALTER TABLE plugin_star OWNER TO dchq;

--
-- TOC entry 265 (class 1259 OID 141320)
-- Name: port_bindings; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE port_bindings (
    container_id character varying(255) NOT NULL,
    private_port integer,
    public_port integer,
    protocol character varying(255)
);


ALTER TABLE port_bindings OWNER TO dchq;

--
-- TOC entry 266 (class 1259 OID 141326)
-- Name: profile; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE profile (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    container_cap integer,
    cpu_cap integer,
    default_profile boolean,
    memory_cap integer,
    name character varying(255),
    storage_cap integer,
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE profile OWNER TO dchq;

--
-- TOC entry 267 (class 1259 OID 141334)
-- Name: provision; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    active_task character varying(255),
    app_backup_profile character varying(255),
    app_backup_profile_active boolean,
    appcdprofile character varying(255),
    appcdprofile_active boolean,
    app_plugin_profile character varying(255),
    app_plugin_profile_active boolean,
    app_rollback_profile character varying(255),
    app_rollback_profile_active boolean,
    app_scale_in_profile character varying(255),
    app_scale_in_profile_active boolean,
    app_scale_out_profile character varying(255),
    app_scale_out_profile_active boolean,
    auto_heal boolean,
    blueprint character varying(255),
    current_cpus integer,
    current_mem double precision,
    data_center_name character varying(255),
    description text,
    mem_in_use boolean,
    name character varying(255),
    provision_state character varying(255),
    reason text,
    tags character varying(255),
    version character varying(255),
    warnings character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL,
    data_center_id character varying(255)
);


ALTER TABLE provision OWNER TO dchq;

--
-- TOC entry 295 (class 1259 OID 142466)
-- Name: provision_containers; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_containers (
    provision_id character varying(255) NOT NULL,
    containers_id character varying(255) NOT NULL
);


ALTER TABLE provision_containers OWNER TO dchq;

--
-- TOC entry 268 (class 1259 OID 141348)
-- Name: provision_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_entitled_groups_pks (
    provision_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE provision_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 269 (class 1259 OID 141354)
-- Name: provision_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_entitled_orgs (
    provision_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE provision_entitled_orgs OWNER TO dchq;

--
-- TOC entry 270 (class 1259 OID 141362)
-- Name: provision_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_entitled_user_groups (
    provision_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE provision_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 271 (class 1259 OID 141370)
-- Name: provision_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_entitled_users (
    provision_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE provision_entitled_users OWNER TO dchq;

--
-- TOC entry 272 (class 1259 OID 141378)
-- Name: provision_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_entitled_users_pks (
    provision_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE provision_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 273 (class 1259 OID 141384)
-- Name: provision_provision_tasks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_provision_tasks (
    provision_id character varying(255) NOT NULL,
    provision_tasks_id character varying(255) NOT NULL
);


ALTER TABLE provision_provision_tasks OWNER TO dchq;

--
-- TOC entry 274 (class 1259 OID 141390)
-- Name: provision_task; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_task (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    ended timestamp without time zone,
    error_msg text,
    note text,
    provision_task_status character varying(255),
    provision_task_type character varying(255),
    retry_attempts integer,
    scheduled timestamp without time zone,
    started timestamp without time zone,
    owner_id character varying(255),
    provision_id character varying(255)
);


ALTER TABLE provision_task OWNER TO dchq;

--
-- TOC entry 275 (class 1259 OID 141398)
-- Name: provision_task_containers; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE provision_task_containers (
    provision_task_id character varying(255) NOT NULL,
    containers character varying(255)
);


ALTER TABLE provision_task_containers OWNER TO dchq;

--
-- TOC entry 276 (class 1259 OID 141404)
-- Name: registry_account; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE registry_account (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    entitlement character varying(255),
    entitlement_type character varying(255),
    account_type character varying(255),
    email character varying(255),
    group_name character varying(255),
    hardware_id character varying(255),
    image_id character varying(255),
    image_password character varying(255),
    image_username character varying(255),
    name character varying(255),
    network_id character varying(255),
    password text,
    reference_id character varying(255),
    region character varying(255),
    security_group_id character varying(255),
    url character varying(255),
    username character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE registry_account OWNER TO dchq;

--
-- TOC entry 277 (class 1259 OID 141412)
-- Name: registry_account_entitled_groups_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE registry_account_entitled_groups_pks (
    registry_account_id character varying(255) NOT NULL,
    entitled_groups_pks character varying(255)
);


ALTER TABLE registry_account_entitled_groups_pks OWNER TO dchq;

--
-- TOC entry 278 (class 1259 OID 141418)
-- Name: registry_account_entitled_orgs; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE registry_account_entitled_orgs (
    registry_account_id character varying(255) NOT NULL,
    entitled_orgs_id character varying(255) NOT NULL
);


ALTER TABLE registry_account_entitled_orgs OWNER TO dchq;

--
-- TOC entry 279 (class 1259 OID 141426)
-- Name: registry_account_entitled_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE registry_account_entitled_user_groups (
    registry_account_id character varying(255) NOT NULL,
    entitled_user_groups_id character varying(255) NOT NULL
);


ALTER TABLE registry_account_entitled_user_groups OWNER TO dchq;

--
-- TOC entry 280 (class 1259 OID 141434)
-- Name: registry_account_entitled_users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE registry_account_entitled_users (
    registry_account_id character varying(255) NOT NULL,
    entitled_users_id character varying(255) NOT NULL
);


ALTER TABLE registry_account_entitled_users OWNER TO dchq;

--
-- TOC entry 281 (class 1259 OID 141442)
-- Name: registry_account_entitled_users_pks; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE registry_account_entitled_users_pks (
    registry_account_id character varying(255) NOT NULL,
    entitled_users_pks character varying(255)
);


ALTER TABLE registry_account_entitled_users_pks OWNER TO dchq;

--
-- TOC entry 282 (class 1259 OID 141448)
-- Name: server_monitor_stat; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE server_monitor_stat (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    containers integer,
    cores integer,
    dt timestamp without time zone,
    images integer,
    proc_cpu_per double precision,
    proc_mem_resident double precision,
    server_id character varying(255),
    total_memory double precision
);


ALTER TABLE server_monitor_stat OWNER TO dchq;

--
-- TOC entry 283 (class 1259 OID 141456)
-- Name: server_monitor_stat_disk_statuses; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE server_monitor_stat_disk_statuses (
    server_monitor_stat_id character varying(255) NOT NULL,
    avail character varying(255),
    file_system character varying(255),
    mounted_on character varying(255),
    size_ character varying(255),
    type_ character varying(255),
    disk_perc double precision,
    used character varying(255)
);


ALTER TABLE server_monitor_stat_disk_statuses OWNER TO dchq;

--
-- TOC entry 284 (class 1259 OID 141462)
-- Name: star; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE star (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    blueprint_id character varying(255),
    dt timestamp without time zone,
    owner_id character varying(255)
);


ALTER TABLE star OWNER TO dchq;

--
-- TOC entry 285 (class 1259 OID 141470)
-- Name: sys_info; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE sys_info (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    agent_version character varying(255),
    cache_size character varying(255),
    cores character varying(255),
    cpu_vendor character varying(255),
    mhz character varying(255),
    model character varying(255),
    physical_cpus character varying(255),
    total_cpus character varying(255),
    docker_root_dir character varying(255),
    ipv4forwarding character varying(255),
    index_server_address character varying(255),
    labels character varying(255),
    ncpu integer,
    nfd integer,
    ngoroutines integer,
    containers integer,
    debug boolean,
    driver character varying(255),
    images integer,
    init_path character varying(255),
    init_sha1 character varying(255),
    kernel_version character varying(255),
    memory_limit boolean,
    fqdn character varying(255),
    hostname character varying(255),
    java_home character varying(255),
    javavmversion character varying(255),
    java_vendor character varying(255),
    key_ character varying(255),
    default_gateway character varying(255),
    domain_name character varying(255),
    interf character varying(255),
    ip character varying(255),
    mac character varying(255),
    netmask character varying(255),
    primary_dns character varying(255),
    secondary_dns character varying(255),
    arch character varying(255),
    code_name character varying(255),
    cpu_endian character varying(255),
    date_model character varying(255),
    description character varying(255),
    machine character varying(255),
    name character varying(255),
    patch_level character varying(255),
    vendor character varying(255),
    vendor_version character varying(255),
    version character varying(255),
    combined double precision NOT NULL,
    idle double precision NOT NULL,
    irq double precision NOT NULL,
    nice double precision NOT NULL,
    sys double precision NOT NULL,
    user_perc double precision NOT NULL,
    wait double precision NOT NULL,
    free double precision NOT NULL,
    free_perc double precision NOT NULL,
    total double precision NOT NULL,
    used double precision NOT NULL,
    uptime character varying(255)
);


ALTER TABLE sys_info OWNER TO dchq;

--
-- TOC entry 286 (class 1259 OID 141478)
-- Name: sys_info_disk_statuses; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE sys_info_disk_statuses (
    sys_info_id character varying(255) NOT NULL,
    avail character varying(255),
    file_system character varying(255),
    mounted_on character varying(255),
    size_ character varying(255),
    type_ character varying(255),
    disk_perc double precision,
    used character varying(255)
);


ALTER TABLE sys_info_disk_statuses OWNER TO dchq;

--
-- TOC entry 287 (class 1259 OID 141484)
-- Name: tenant; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE tenant (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    name character varying(255),
    owner_id character varying(255)
);


ALTER TABLE tenant OWNER TO dchq;

--
-- TOC entry 288 (class 1259 OID 141492)
-- Name: test; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE test (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    name character varying(255),
    owner_id character varying(255)
);


ALTER TABLE test OWNER TO dchq;

--
-- TOC entry 289 (class 1259 OID 141500)
-- Name: user_group; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE user_group (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    name character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL
);


ALTER TABLE user_group OWNER TO dchq;

--
-- TOC entry 290 (class 1259 OID 141508)
-- Name: users; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE users (
    id character varying(255) NOT NULL,
    created_by character varying(255),
    created_date timestamp without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp without time zone,
    deleted boolean,
    inactive boolean,
    lock_version integer,
    owner_pk character varying(255),
    uuid character varying(255),
    tenant_pk character varying(255),
    company character varying(255),
    cpu_used integer,
    email character varying(255),
    enabled boolean,
    firstname character varying(255),
    job_title character varying(255),
    lastname character varying(255),
    memory_used integer,
    password character varying(255),
    phone_number character varying(255),
    username character varying(255),
    owner_id character varying(255),
    tenant_id character varying(255) NOT NULL,
    organization_id character varying(255),
    preferred_data_center_id character varying(255),
    profile_id character varying(255)
);


ALTER TABLE users OWNER TO dchq;

--
-- TOC entry 291 (class 1259 OID 141516)
-- Name: users_authorities; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE users_authorities (
    users_id character varying(255) NOT NULL,
    authorities character varying(255)
);


ALTER TABLE users_authorities OWNER TO dchq;

--
-- TOC entry 292 (class 1259 OID 141522)
-- Name: users_user_group_ids; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE users_user_group_ids (
    users_id character varying(255) NOT NULL,
    user_group_ids character varying(255)
);


ALTER TABLE users_user_group_ids OWNER TO dchq;

--
-- TOC entry 293 (class 1259 OID 141528)
-- Name: users_user_groups; Type: TABLE; Schema: public; Owner: dchq; Tablespace: 
--

CREATE TABLE users_user_groups (
    users_id character varying(255) NOT NULL,
    user_groups_id character varying(255) NOT NULL
);


ALTER TABLE users_user_groups OWNER TO dchq;

--
-- TOC entry 2541 (class 2606 OID 140753)
-- Name: agent_ping_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY agent_ping
    ADD CONSTRAINT agent_ping_pkey PRIMARY KEY (id);


--
-- TOC entry 2543 (class 2606 OID 140761)
-- Name: app_backup_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_backup_profiles
    ADD CONSTRAINT app_backup_profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2547 (class 2606 OID 140775)
-- Name: app_backups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_backups
    ADD CONSTRAINT app_backups_pkey PRIMARY KEY (id);


--
-- TOC entry 2681 (class 2606 OID 142453)
-- Name: app_config_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_config
    ADD CONSTRAINT app_config_pkey PRIMARY KEY (id);


--
-- TOC entry 2551 (class 2606 OID 140789)
-- Name: app_plugin_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_plugin_profiles
    ADD CONSTRAINT app_plugin_profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2555 (class 2606 OID 140803)
-- Name: app_rollback_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_rollback_profile
    ADD CONSTRAINT app_rollback_profile_pkey PRIMARY KEY (id);


--
-- TOC entry 2557 (class 2606 OID 140823)
-- Name: app_scale_in_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_scale_in_profiles
    ADD CONSTRAINT app_scale_in_profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2559 (class 2606 OID 140837)
-- Name: app_scale_out_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_scale_out_profiles
    ADD CONSTRAINT app_scale_out_profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 2563 (class 2606 OID 140859)
-- Name: blueprint_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY blueprint_entitled_orgs
    ADD CONSTRAINT blueprint_entitled_orgs_pkey PRIMARY KEY (blueprint_id, entitled_orgs_id);


--
-- TOC entry 2565 (class 2606 OID 140867)
-- Name: blueprint_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY blueprint_entitled_user_groups
    ADD CONSTRAINT blueprint_entitled_user_groups_pkey PRIMARY KEY (blueprint_id, entitled_user_groups_id);


--
-- TOC entry 2567 (class 2606 OID 140875)
-- Name: blueprint_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY blueprint_entitled_users
    ADD CONSTRAINT blueprint_entitled_users_pkey PRIMARY KEY (blueprint_id, entitled_users_id);


--
-- TOC entry 2561 (class 2606 OID 140845)
-- Name: blueprint_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY blueprint
    ADD CONSTRAINT blueprint_pkey PRIMARY KEY (id);


--
-- TOC entry 2575 (class 2606 OID 140915)
-- Name: build_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY build_entitled_orgs
    ADD CONSTRAINT build_entitled_orgs_pkey PRIMARY KEY (build_id, entitled_orgs_id);


--
-- TOC entry 2577 (class 2606 OID 140923)
-- Name: build_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY build_entitled_user_groups
    ADD CONSTRAINT build_entitled_user_groups_pkey PRIMARY KEY (build_id, entitled_user_groups_id);


--
-- TOC entry 2579 (class 2606 OID 140931)
-- Name: build_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY build_entitled_users
    ADD CONSTRAINT build_entitled_users_pkey PRIMARY KEY (build_id, entitled_users_id);


--
-- TOC entry 2571 (class 2606 OID 140895)
-- Name: build_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY build
    ADD CONSTRAINT build_pkey PRIMARY KEY (id);


--
-- TOC entry 2581 (class 2606 OID 140945)
-- Name: build_task_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY build_task
    ADD CONSTRAINT build_task_pkey PRIMARY KEY (id);


--
-- TOC entry 2583 (class 2606 OID 140953)
-- Name: certified_image_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY certified_image
    ADD CONSTRAINT certified_image_pkey PRIMARY KEY (id);


--
-- TOC entry 2587 (class 2606 OID 140969)
-- Name: container_backup_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY container_backup_profile
    ADD CONSTRAINT container_backup_profile_pkey PRIMARY KEY (id);


--
-- TOC entry 2589 (class 2606 OID 140995)
-- Name: container_dependencies_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY container_dependencies
    ADD CONSTRAINT container_dependencies_pkey PRIMARY KEY (container_id, dependencies_key);


--
-- TOC entry 2591 (class 2606 OID 141021)
-- Name: container_monitor_stat_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY container_monitor_stat
    ADD CONSTRAINT container_monitor_stat_pkey PRIMARY KEY (id);


--
-- TOC entry 2585 (class 2606 OID 140961)
-- Name: container_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY container
    ADD CONSTRAINT container_pkey PRIMARY KEY (id);


--
-- TOC entry 2593 (class 2606 OID 141029)
-- Name: container_plugin_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY container_plugin_profile
    ADD CONSTRAINT container_plugin_profile_pkey PRIMARY KEY (id);


--
-- TOC entry 2599 (class 2606 OID 141075)
-- Name: data_center_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY data_center_entitled_orgs
    ADD CONSTRAINT data_center_entitled_orgs_pkey PRIMARY KEY (data_center_id, entitled_orgs_id);


--
-- TOC entry 2601 (class 2606 OID 141083)
-- Name: data_center_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY data_center_entitled_user_groups
    ADD CONSTRAINT data_center_entitled_user_groups_pkey PRIMARY KEY (data_center_id, entitled_user_groups_id);


--
-- TOC entry 2603 (class 2606 OID 141091)
-- Name: data_center_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY data_center_entitled_users
    ADD CONSTRAINT data_center_entitled_users_pkey PRIMARY KEY (data_center_id, entitled_users_id);


--
-- TOC entry 2597 (class 2606 OID 141061)
-- Name: data_center_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY data_center
    ADD CONSTRAINT data_center_pkey PRIMARY KEY (id);


--
-- TOC entry 2607 (class 2606 OID 141119)
-- Name: docker_server_image_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY docker_server_image
    ADD CONSTRAINT docker_server_image_pkey PRIMARY KEY (id);


--
-- TOC entry 2605 (class 2606 OID 141105)
-- Name: docker_server_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY docker_server
    ADD CONSTRAINT docker_server_pkey PRIMARY KEY (id);


--
-- TOC entry 2609 (class 2606 OID 141127)
-- Name: docker_server_task_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY docker_server_task
    ADD CONSTRAINT docker_server_task_pkey PRIMARY KEY (id);


--
-- TOC entry 2611 (class 2606 OID 141135)
-- Name: email_alert_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY email_alert
    ADD CONSTRAINT email_alert_pkey PRIMARY KEY (id);


--
-- TOC entry 2613 (class 2606 OID 141143)
-- Name: image_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 2615 (class 2606 OID 141163)
-- Name: image_plugins_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY image_plugins
    ADD CONSTRAINT image_plugins_pkey PRIMARY KEY (image_id, plugins_id);


--
-- TOC entry 2619 (class 2606 OID 141197)
-- Name: inbox_message_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY inbox_message_entitled_orgs
    ADD CONSTRAINT inbox_message_entitled_orgs_pkey PRIMARY KEY (inbox_message_id, entitled_orgs_id);


--
-- TOC entry 2621 (class 2606 OID 141205)
-- Name: inbox_message_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY inbox_message_entitled_user_groups
    ADD CONSTRAINT inbox_message_entitled_user_groups_pkey PRIMARY KEY (inbox_message_id, entitled_user_groups_id);


--
-- TOC entry 2623 (class 2606 OID 141213)
-- Name: inbox_message_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY inbox_message_entitled_users
    ADD CONSTRAINT inbox_message_entitled_users_pkey PRIMARY KEY (inbox_message_id, entitled_users_id);


--
-- TOC entry 2617 (class 2606 OID 141189)
-- Name: inbox_message_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY inbox_message
    ADD CONSTRAINT inbox_message_pkey PRIMARY KEY (id);


--
-- TOC entry 2625 (class 2606 OID 141239)
-- Name: organization_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 2627 (class 2606 OID 141247)
-- Name: password_reset_token_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY password_reset_token
    ADD CONSTRAINT password_reset_token_pkey PRIMARY KEY (id);


--
-- TOC entry 2631 (class 2606 OID 141269)
-- Name: plugin_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY plugin_entitled_orgs
    ADD CONSTRAINT plugin_entitled_orgs_pkey PRIMARY KEY (plugin_id, entitled_orgs_id);


--
-- TOC entry 2633 (class 2606 OID 141277)
-- Name: plugin_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY plugin_entitled_user_groups
    ADD CONSTRAINT plugin_entitled_user_groups_pkey PRIMARY KEY (plugin_id, entitled_user_groups_id);


--
-- TOC entry 2635 (class 2606 OID 141285)
-- Name: plugin_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY plugin_entitled_users
    ADD CONSTRAINT plugin_entitled_users_pkey PRIMARY KEY (plugin_id, entitled_users_id);


--
-- TOC entry 2629 (class 2606 OID 141255)
-- Name: plugin_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY plugin
    ADD CONSTRAINT plugin_pkey PRIMARY KEY (id);


--
-- TOC entry 2637 (class 2606 OID 141305)
-- Name: plugin_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY plugin_profile
    ADD CONSTRAINT plugin_profile_pkey PRIMARY KEY (id);


--
-- TOC entry 2639 (class 2606 OID 141319)
-- Name: plugin_star_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY plugin_star
    ADD CONSTRAINT plugin_star_pkey PRIMARY KEY (id);


--
-- TOC entry 2641 (class 2606 OID 141333)
-- Name: profile_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- TOC entry 2645 (class 2606 OID 141361)
-- Name: provision_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision_entitled_orgs
    ADD CONSTRAINT provision_entitled_orgs_pkey PRIMARY KEY (provision_id, entitled_orgs_id);


--
-- TOC entry 2647 (class 2606 OID 141369)
-- Name: provision_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision_entitled_user_groups
    ADD CONSTRAINT provision_entitled_user_groups_pkey PRIMARY KEY (provision_id, entitled_user_groups_id);


--
-- TOC entry 2649 (class 2606 OID 141377)
-- Name: provision_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision_entitled_users
    ADD CONSTRAINT provision_entitled_users_pkey PRIMARY KEY (provision_id, entitled_users_id);


--
-- TOC entry 2643 (class 2606 OID 141341)
-- Name: provision_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision
    ADD CONSTRAINT provision_pkey PRIMARY KEY (id);


--
-- TOC entry 2653 (class 2606 OID 141397)
-- Name: provision_task_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision_task
    ADD CONSTRAINT provision_task_pkey PRIMARY KEY (id);



--
-- TOC entry 2657 (class 2606 OID 141425)
-- Name: registry_account_entitled_orgs_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY registry_account_entitled_orgs
    ADD CONSTRAINT registry_account_entitled_orgs_pkey PRIMARY KEY (registry_account_id, entitled_orgs_id);


--
-- TOC entry 2659 (class 2606 OID 141433)
-- Name: registry_account_entitled_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY registry_account_entitled_user_groups
    ADD CONSTRAINT registry_account_entitled_user_groups_pkey PRIMARY KEY (registry_account_id, entitled_user_groups_id);


--
-- TOC entry 2661 (class 2606 OID 141441)
-- Name: registry_account_entitled_users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY registry_account_entitled_users
    ADD CONSTRAINT registry_account_entitled_users_pkey PRIMARY KEY (registry_account_id, entitled_users_id);


--
-- TOC entry 2655 (class 2606 OID 141411)
-- Name: registry_account_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY registry_account
    ADD CONSTRAINT registry_account_pkey PRIMARY KEY (id);



--
-- TOC entry 2663 (class 2606 OID 141455)
-- Name: server_monitor_stat_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY server_monitor_stat
    ADD CONSTRAINT server_monitor_stat_pkey PRIMARY KEY (id);


--
-- TOC entry 2665 (class 2606 OID 141469)
-- Name: star_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY star
    ADD CONSTRAINT star_pkey PRIMARY KEY (id);


--
-- TOC entry 2667 (class 2606 OID 141477)
-- Name: sys_info_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY sys_info
    ADD CONSTRAINT sys_info_pkey PRIMARY KEY (id);


--
-- TOC entry 2669 (class 2606 OID 141491)
-- Name: tenant_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY tenant
    ADD CONSTRAINT tenant_pkey PRIMARY KEY (id);


--
-- TOC entry 2673 (class 2606 OID 141499)
-- Name: test_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- TOC entry 2651 (class 2606 OID 141551)
-- Name: uk_68ar1h02lbcu399w5thdwary; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision_provision_tasks
    ADD CONSTRAINT uk_68ar1h02lbcu399w5thdwary UNIQUE (provision_tasks_id);


--
-- TOC entry 2683 (class 2606 OID 142473)
-- Name: uk_8e43klacv5of1d2xo6h95ag2b; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY provision_containers
    ADD CONSTRAINT uk_8e43klacv5of1d2xo6h95ag2b UNIQUE (containers_id);


--
-- TOC entry 2573 (class 2606 OID 141545)
-- Name: uk_bqtuvg3gpen3ci63l1t8cytxr; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY build_build_tasks
    ADD CONSTRAINT uk_bqtuvg3gpen3ci63l1t8cytxr UNIQUE (build_tasks_id);


--
-- TOC entry 2671 (class 2606 OID 141553)
-- Name: uk_dcxf3ksi0gyn1tieeq0id96lm; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY tenant
    ADD CONSTRAINT uk_dcxf3ksi0gyn1tieeq0id96lm UNIQUE (name);


--
-- TOC entry 2569 (class 2606 OID 141543)
-- Name: uk_eursd4fad9grbies48dv90jvv; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY blueprint_images
    ADD CONSTRAINT uk_eursd4fad9grbies48dv90jvv UNIQUE (images_id);


--
-- TOC entry 2553 (class 2606 OID 141541)
-- Name: uk_im0bgrheig3x37dg0xoryprqv; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_plugin_profiles_containers
    ADD CONSTRAINT uk_im0bgrheig3x37dg0xoryprqv UNIQUE (containers_id);


--
-- TOC entry 2545 (class 2606 OID 141537)
-- Name: uk_in1ewc69fmv6s2phdk3toke3o; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_backup_profiles_containers
    ADD CONSTRAINT uk_in1ewc69fmv6s2phdk3toke3o UNIQUE (containers_id);


--
-- TOC entry 2549 (class 2606 OID 141539)
-- Name: uk_rt9bcqrd1gfngmxbwuh9n0jad; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY app_backups_containers
    ADD CONSTRAINT uk_rt9bcqrd1gfngmxbwuh9n0jad UNIQUE (containers_id);


--
-- TOC entry 2595 (class 2606 OID 141547)
-- Name: uk_squls3wkc0klvub8tvcbue1lb; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY container_plugin_profile_plugin_profiles
    ADD CONSTRAINT uk_squls3wkc0klvub8tvcbue1lb UNIQUE (plugin_profiles_id);


--
-- TOC entry 2675 (class 2606 OID 141507)
-- Name: user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


--
-- TOC entry 2677 (class 2606 OID 141515)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2679 (class 2606 OID 141535)
-- Name: users_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: dchq; Tablespace: 
--

ALTER TABLE ONLY users_user_groups
    ADD CONSTRAINT users_user_groups_pkey PRIMARY KEY (users_id, user_groups_id);



--
-- TOC entry 2759 (class 2606 OID 141904)
-- Name: fk_1cdhayhi5blyo8fxwwiil668j; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_users_pks
    ADD CONSTRAINT fk_1cdhayhi5blyo8fxwwiil668j FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2846 (class 2606 OID 142349)
-- Name: fk_1dg427qt898j7shqnvv5s617p; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_1dg427qt898j7shqnvv5s617p FOREIGN KEY (organization_id) REFERENCES organization(id);


--
-- TOC entry 2728 (class 2606 OID 141749)
-- Name: fk_1dq4ptx0ix7q1euxt68eeoc3i; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_users
    ADD CONSTRAINT fk_1dq4ptx0ix7q1euxt68eeoc3i FOREIGN KEY (build_id) REFERENCES build(id);


--
-- TOC entry 2816 (class 2606 OID 142199)
-- Name: fk_1gwcs8lqndei8lua56b2bebx; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_orgs
    ADD CONSTRAINT fk_1gwcs8lqndei8lua56b2bebx FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2832 (class 2606 OID 142279)
-- Name: fk_1gweq3xr4exxrk99lo7b9rksn; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_user_groups
    ADD CONSTRAINT fk_1gweq3xr4exxrk99lo7b9rksn FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2792 (class 2606 OID 142069)
-- Name: fk_28dvg4ueatm79110vfe0w5p8; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY password_reset_token
    ADD CONSTRAINT fk_28dvg4ueatm79110vfe0w5p8 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2764 (class 2606 OID 141929)
-- Name: fk_2e9laee548gc0bmrt762tvfbq; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_image
    ADD CONSTRAINT fk_2e9laee548gc0bmrt762tvfbq FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2704 (class 2606 OID 141629)
-- Name: fk_2f15axmrb12wr5b3yncn9955; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint
    ADD CONSTRAINT fk_2f15axmrb12wr5b3yncn9955 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2773 (class 2606 OID 141974)
-- Name: fk_2gpn7h0dyqc8hnqxo7m27kftg; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_envs
    ADD CONSTRAINT fk_2gpn7h0dyqc8hnqxo7m27kftg FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2782 (class 2606 OID 142019)
-- Name: fk_2tm36m9oeie3bwkd1mqnjknaf; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message_entitled_orgs
    ADD CONSTRAINT fk_2tm36m9oeie3bwkd1mqnjknaf FOREIGN KEY (inbox_message_id) REFERENCES inbox_message(id);


--
-- TOC entry 2739 (class 2606 OID 141804)
-- Name: fk_2u2byt6qkmxv2vid8hc5pgye3; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_dependencies
    ADD CONSTRAINT fk_2u2byt6qkmxv2vid8hc5pgye3 FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2737 (class 2606 OID 141794)
-- Name: fk_332cfn00rrxo0bto312otg70e; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_cap_drop
    ADD CONSTRAINT fk_332cfn00rrxo0bto312otg70e FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2835 (class 2606 OID 142294)
-- Name: fk_33jv1tgkpqh16q9a42n79hul4; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_users
    ADD CONSTRAINT fk_33jv1tgkpqh16q9a42n79hul4 FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2729 (class 2606 OID 141754)
-- Name: fk_37sj8d4qnfnr4awd0by5uotl3; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_users_pks
    ADD CONSTRAINT fk_37sj8d4qnfnr4awd0by5uotl3 FOREIGN KEY (build_id) REFERENCES build(id);


--
-- TOC entry 2820 (class 2606 OID 142219)
-- Name: fk_39qt2cq9lr48e9x2tgbbeix0x; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_users
    ADD CONSTRAINT fk_39qt2cq9lr48e9x2tgbbeix0x FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2760 (class 2606 OID 141909)
-- Name: fk_3b9o18x9g0csuyhhmjxnlb31o; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server
    ADD CONSTRAINT fk_3b9o18x9g0csuyhhmjxnlb31o FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2767 (class 2606 OID 141944)
-- Name: fk_3cvu9kc9w5dtrul421argk3fn; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_image
    ADD CONSTRAINT fk_3cvu9kc9w5dtrul421argk3fn FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2804 (class 2606 OID 142129)
-- Name: fk_3ecf7itvamh48fy4t6fcq21cj; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_envs
    ADD CONSTRAINT fk_3ecf7itvamh48fy4t6fcq21cj FOREIGN KEY (plugin_id) REFERENCES plugin(id);


--
-- TOC entry 2825 (class 2606 OID 142244)
-- Name: fk_3pqw1gjpurvuw22on90qrqppa; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_task
    ADD CONSTRAINT fk_3pqw1gjpurvuw22on90qrqppa FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2807 (class 2606 OID 142144)
-- Name: fk_4g7si3ae1s5h2afvw5kbgq4ex; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_star
    ADD CONSTRAINT fk_4g7si3ae1s5h2afvw5kbgq4ex FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2755 (class 2606 OID 141884)
-- Name: fk_4tyv6hgk9xinbf5mj1o95lxcc; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_user_groups
    ADD CONSTRAINT fk_4tyv6hgk9xinbf5mj1o95lxcc FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2778 (class 2606 OID 141999)
-- Name: fk_5asjxuhir9c4g7gf2f0emoejy; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_volumes
    ADD CONSTRAINT fk_5asjxuhir9c4g7gf2f0emoejy FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2815 (class 2606 OID 142194)
-- Name: fk_62yfgqdqqjw93elqxugmrke71; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_orgs
    ADD CONSTRAINT fk_62yfgqdqqjw93elqxugmrke71 FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2822 (class 2606 OID 142229)
-- Name: fk_68ar1h02lbcu399w5thdwary; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_provision_tasks
    ADD CONSTRAINT fk_68ar1h02lbcu399w5thdwary FOREIGN KEY (provision_tasks_id) REFERENCES provision_task(id);


--
-- TOC entry 2702 (class 2606 OID 141619)
-- Name: fk_6cx96x5rmq66h157lsyskg6wh; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_scale_out_profile_cluster_profiles
    ADD CONSTRAINT fk_6cx96x5rmq66h157lsyskg6wh FOREIGN KEY (app_scale_out_profile_id) REFERENCES app_scale_out_profiles(id);


--
-- TOC entry 2707 (class 2606 OID 141644)
-- Name: fk_6e46u1a3cnviyyunptgas93kf; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_groups_pks
    ADD CONSTRAINT fk_6e46u1a3cnviyyunptgas93kf FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);


--
-- TOC entry 2692 (class 2606 OID 141569)
-- Name: fk_6u6vdfqyax7od7imy07qbhyqi; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_backups
    ADD CONSTRAINT fk_6u6vdfqyax7od7imy07qbhyqi FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2827 (class 2606 OID 142254)
-- Name: fk_70kwq3eepor6ujjc9qjr5yq7j; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account
    ADD CONSTRAINT fk_70kwq3eepor6ujjc9qjr5yq7j FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2712 (class 2606 OID 141669)
-- Name: fk_70s50ugjvtkaktwr16oxt2575; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_users
    ADD CONSTRAINT fk_70s50ugjvtkaktwr16oxt2575 FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2718 (class 2606 OID 141699)
-- Name: fk_71ckc7ir9c26dh9b0sxcksbmi; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build
    ADD CONSTRAINT fk_71ckc7ir9c26dh9b0sxcksbmi FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2763 (class 2606 OID 141924)
-- Name: fk_7b6kmbxx7lpljhp75g7wkbx09; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_envs
    ADD CONSTRAINT fk_7b6kmbxx7lpljhp75g7wkbx09 FOREIGN KEY (docker_server_id) REFERENCES docker_server(id);


--
-- TOC entry 2740 (class 2606 OID 141809)
-- Name: fk_7bmotgxjv2l47eoibe915up85; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_dns
    ADD CONSTRAINT fk_7bmotgxjv2l47eoibe915up85 FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2703 (class 2606 OID 141624)
-- Name: fk_7ekaxq1y7tpbjttyu1au976q6; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_scale_out_profiles
    ADD CONSTRAINT fk_7ekaxq1y7tpbjttyu1au976q6 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2848 (class 2606 OID 142359)
-- Name: fk_7s5nlreekaxdbfml4ofky7utw; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_7s5nlreekaxdbfml4ofky7utw FOREIGN KEY (profile_id) REFERENCES profile(id);


--
-- TOC entry 2731 (class 2606 OID 141764)
-- Name: fk_7t3x539xcjg8cms4n2fjtuwsg; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY certified_image
    ADD CONSTRAINT fk_7t3x539xcjg8cms4n2fjtuwsg FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2829 (class 2606 OID 142264)
-- Name: fk_85equ0ulmcsmf2lbdd811u905; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_groups_pks
    ADD CONSTRAINT fk_85equ0ulmcsmf2lbdd811u905 FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2698 (class 2606 OID 141599)
-- Name: fk_8acbexkfrprcpq3es5qfqqv48; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_rollback_profile
    ADD CONSTRAINT fk_8acbexkfrprcpq3es5qfqqv48 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2852 (class 2606 OID 142379)
-- Name: fk_8cv2deco6evdweubh3gw9v1id; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users_user_groups
    ADD CONSTRAINT fk_8cv2deco6evdweubh3gw9v1id FOREIGN KEY (users_id) REFERENCES users(id);


--
-- TOC entry 2856 (class 2606 OID 142474)
-- Name: fk_8e43klacv5of1d2xo6h95ag2b; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_containers
    ADD CONSTRAINT fk_8e43klacv5of1d2xo6h95ag2b FOREIGN KEY (containers_id) REFERENCES container(id);


--
-- TOC entry 2828 (class 2606 OID 142259)
-- Name: fk_8l7w1g8wm8ouh6uefdx61b4br; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account
    ADD CONSTRAINT fk_8l7w1g8wm8ouh6uefdx61b4br FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2745 (class 2606 OID 141834)
-- Name: fk_8pbffpc1s868mwg0qpdtpgxcd; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_plugin_profile_plugin_profiles
    ADD CONSTRAINT fk_8pbffpc1s868mwg0qpdtpgxcd FOREIGN KEY (container_plugin_profile_id) REFERENCES container_plugin_profile(id);


--
-- TOC entry 2708 (class 2606 OID 141649)
-- Name: fk_96vw2hs0uq7xbrwm29fwhg4gd; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_orgs
    ADD CONSTRAINT fk_96vw2hs0uq7xbrwm29fwhg4gd FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2795 (class 2606 OID 142084)
-- Name: fk_96wryl3na8tt1ni8a04dn5g5e; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin
    ADD CONSTRAINT fk_96wryl3na8tt1ni8a04dn5g5e FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2791 (class 2606 OID 142064)
-- Name: fk_9kpgdoh9i97ubd7blb4jon14t; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT fk_9kpgdoh9i97ubd7blb4jon14t FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2847 (class 2606 OID 142354)
-- Name: fk_9qqkjki4csaxwtig9brgcrfug; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_9qqkjki4csaxwtig9brgcrfug FOREIGN KEY (preferred_data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2717 (class 2606 OID 141694)
-- Name: fk_9tnqpj9ihr8892x6nbeikdtjv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build
    ADD CONSTRAINT fk_9tnqpj9ihr8892x6nbeikdtjv FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2779 (class 2606 OID 142004)
-- Name: fk_9u0hncbll24475ln3shp4pktd; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message
    ADD CONSTRAINT fk_9u0hncbll24475ln3shp4pktd FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2843 (class 2606 OID 142334)
-- Name: fk_9ubn4j9v4kpds9teitt19lsck; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT fk_9ubn4j9v4kpds9teitt19lsck FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2805 (class 2606 OID 142134)
-- Name: fk_9yraqr48188gyuffcwdwh2s4l; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_profile
    ADD CONSTRAINT fk_9yraqr48188gyuffcwdwh2s4l FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2819 (class 2606 OID 142214)
-- Name: fk_almfvqnluhk4hhk5mdau54bs1; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_users
    ADD CONSTRAINT fk_almfvqnluhk4hhk5mdau54bs1 FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2803 (class 2606 OID 142124)
-- Name: fk_an3t3pms9s5mlbcg4g162dooy; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_users_pks
    ADD CONSTRAINT fk_an3t3pms9s5mlbcg4g162dooy FOREIGN KEY (plugin_id) REFERENCES plugin(id);


--
-- TOC entry 2727 (class 2606 OID 141744)
-- Name: fk_au9362uigfkfej6p4y9gpsrbj; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_users
    ADD CONSTRAINT fk_au9362uigfkfej6p4y9gpsrbj FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2783 (class 2606 OID 142024)
-- Name: fk_auifwad60ktpswtihwwk503u1; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message_entitled_user_groups
    ADD CONSTRAINT fk_auifwad60ktpswtihwwk503u1 FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2748 (class 2606 OID 141849)
-- Name: fk_b6gpajyaijjvdk4k9lt9wt891; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_port_bindings
    ADD CONSTRAINT fk_b6gpajyaijjvdk4k9lt9wt891 FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2732 (class 2606 OID 141769)
-- Name: fk_bbqidhhgbit9q0dbvhdfnxj7e; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container
    ADD CONSTRAINT fk_bbqidhhgbit9q0dbvhdfnxj7e FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2809 (class 2606 OID 142154)
-- Name: fk_be00om1iatk0jh6xf53kbuhk6; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT fk_be00om1iatk0jh6xf53kbuhk6 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2700 (class 2606 OID 141609)
-- Name: fk_bg19w6go66jffxoaw6epxbesx; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_scale_in_profile_cluster_profiles
    ADD CONSTRAINT fk_bg19w6go66jffxoaw6epxbesx FOREIGN KEY (app_scale_in_profile_id) REFERENCES app_scale_in_profiles(id);


--
-- TOC entry 2797 (class 2606 OID 142094)
-- Name: fk_bib8ugmwmcyw85fhumt2w6oh9; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_orgs
    ADD CONSTRAINT fk_bib8ugmwmcyw85fhumt2w6oh9 FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2720 (class 2606 OID 141709)
-- Name: fk_bqtuvg3gpen3ci63l1t8cytxr; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_build_tasks
    ADD CONSTRAINT fk_bqtuvg3gpen3ci63l1t8cytxr FOREIGN KEY (build_tasks_id) REFERENCES build_task(id);


--
-- TOC entry 2751 (class 2606 OID 141864)
-- Name: fk_bv0emqw9t9vs46340b2du2hal; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center
    ADD CONSTRAINT fk_bv0emqw9t9vs46340b2du2hal FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2821 (class 2606 OID 142224)
-- Name: fk_cct4ys8vpncvyvq8s5v9k8lor; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_users_pks
    ADD CONSTRAINT fk_cct4ys8vpncvyvq8s5v9k8lor FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2836 (class 2606 OID 142299)
-- Name: fk_cfyxvb1giuargchxj9hxofhu1; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_users_pks
    ADD CONSTRAINT fk_cfyxvb1giuargchxj9hxofhu1 FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2722 (class 2606 OID 141719)
-- Name: fk_coisd8pyfvryhowptlngoyp73; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_groups_pks
    ADD CONSTRAINT fk_coisd8pyfvryhowptlngoyp73 FOREIGN KEY (build_id) REFERENCES build(id);


--
-- TOC entry 2785 (class 2606 OID 142034)
-- Name: fk_cpk882as8tnatfcm0kryjqwsc; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message_entitled_users
    ADD CONSTRAINT fk_cpk882as8tnatfcm0kryjqwsc FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2742 (class 2606 OID 141819)
-- Name: fk_cuulflp5ha07ydvj221n8kbh; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_envs
    ADD CONSTRAINT fk_cuulflp5ha07ydvj221n8kbh FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2774 (class 2606 OID 141979)
-- Name: fk_ddfxlop1x3l8ifi7u91omph3k; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_plugins
    ADD CONSTRAINT fk_ddfxlop1x3l8ifi7u91omph3k FOREIGN KEY (plugins_id) REFERENCES plugin(id);


--
-- TOC entry 2716 (class 2606 OID 141689)
-- Name: fk_de58oxltyga0fkjs80foll4e; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_images
    ADD CONSTRAINT fk_de58oxltyga0fkjs80foll4e FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);


--
-- TOC entry 2855 (class 2606 OID 142479)
-- Name: fk_dsuw5tj93culdevwxq92vjgk7; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_containers
    ADD CONSTRAINT fk_dsuw5tj93culdevwxq92vjgk7 FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2788 (class 2606 OID 142049)
-- Name: fk_dt9ued0opxsvdup4mgs9qiudn; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY message_entitled_groups_pks
    ADD CONSTRAINT fk_dt9ued0opxsvdup4mgs9qiudn FOREIGN KEY (message_id) REFERENCES inbox_message(id);


--
-- TOC entry 2814 (class 2606 OID 142189)
-- Name: fk_eauv5mc0xvmw5f0bfx36tfxed; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_groups_pks
    ADD CONSTRAINT fk_eauv5mc0xvmw5f0bfx36tfxed FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2784 (class 2606 OID 142029)
-- Name: fk_ef4ovkklvd3p863peprbjcmwp; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message_entitled_user_groups
    ADD CONSTRAINT fk_ef4ovkklvd3p863peprbjcmwp FOREIGN KEY (inbox_message_id) REFERENCES inbox_message(id);


--
-- TOC entry 2761 (class 2606 OID 141914)
-- Name: fk_efb1l4dsveaj12hkdvsx6a3w4; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server
    ADD CONSTRAINT fk_efb1l4dsveaj12hkdvsx6a3w4 FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2768 (class 2606 OID 141949)
-- Name: fk_eid92i73eq4jiwnmkwhd3gjd; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_task
    ADD CONSTRAINT fk_eid92i73eq4jiwnmkwhd3gjd FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2771 (class 2606 OID 141964)
-- Name: fk_ernyi63ow46iflgrqpqe1kdyn; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image
    ADD CONSTRAINT fk_ernyi63ow46iflgrqpqe1kdyn FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2715 (class 2606 OID 141684)
-- Name: fk_eursd4fad9grbies48dv90jvv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_images
    ADD CONSTRAINT fk_eursd4fad9grbies48dv90jvv FOREIGN KEY (images_id) REFERENCES image(id);


--
-- TOC entry 2691 (class 2606 OID 141564)
-- Name: fk_eve11unp89fyliedu0v9e9pgt; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_backup_profiles_containers
    ADD CONSTRAINT fk_eve11unp89fyliedu0v9e9pgt FOREIGN KEY (app_backup_profiles_id) REFERENCES app_backup_profiles(id);


--
-- TOC entry 2841 (class 2606 OID 142324)
-- Name: fk_evf3dlw6sk6l0m3bu6a3uojl; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY test
    ADD CONSTRAINT fk_evf3dlw6sk6l0m3bu6a3uojl FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2794 (class 2606 OID 142079)
-- Name: fk_eydj4q373lhj4njvoyynqsnxa; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin
    ADD CONSTRAINT fk_eydj4q373lhj4njvoyynqsnxa FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2730 (class 2606 OID 141759)
-- Name: fk_f99yqq1k1cuvbjwuawi3t6hw8; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_task
    ADD CONSTRAINT fk_f99yqq1k1cuvbjwuawi3t6hw8 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2781 (class 2606 OID 142014)
-- Name: fk_fahn556wsl82w96k93uti7bgv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message_entitled_orgs
    ADD CONSTRAINT fk_fahn556wsl82w96k93uti7bgv FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2837 (class 2606 OID 142304)
-- Name: fk_fhas7d2vg051rpmwoa4yvb6ha; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY server_monitor_stat_disk_statuses
    ADD CONSTRAINT fk_fhas7d2vg051rpmwoa4yvb6ha FOREIGN KEY (server_monitor_stat_id) REFERENCES server_monitor_stat(id);


--
-- TOC entry 2790 (class 2606 OID 142059)
-- Name: fk_fkveoq1lhpf0yrmdc5i72qoao; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT fk_fkveoq1lhpf0yrmdc5i72qoao FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2839 (class 2606 OID 142314)
-- Name: fk_flu59ukkp1t488aj26xg0jbgi; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY sys_info_disk_statuses
    ADD CONSTRAINT fk_flu59ukkp1t488aj26xg0jbgi FOREIGN KEY (sys_info_id) REFERENCES sys_info(id);


--
-- TOC entry 2756 (class 2606 OID 141889)
-- Name: fk_fmv79d3m38p7022cv45j2q3fv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_user_groups
    ADD CONSTRAINT fk_fmv79d3m38p7022cv45j2q3fv FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2697 (class 2606 OID 141594)
-- Name: fk_fr3frjwqyp1o7r1glt33ie58a; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_plugin_profiles_containers
    ADD CONSTRAINT fk_fr3frjwqyp1o7r1glt33ie58a FOREIGN KEY (app_plugin_profiles_id) REFERENCES app_plugin_profiles(id);


--
-- TOC entry 2830 (class 2606 OID 142269)
-- Name: fk_g5lxqxairqhl2qpwk8g2qk7bj; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_orgs
    ADD CONSTRAINT fk_g5lxqxairqhl2qpwk8g2qk7bj FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2741 (class 2606 OID 141814)
-- Name: fk_g6qo5wxpqaqc07oynv5xk901u; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_dns_search
    ADD CONSTRAINT fk_g6qo5wxpqaqc07oynv5xk901u FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2772 (class 2606 OID 141969)
-- Name: fk_g79te4loyhvkcojrhw4dpjqen; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_commands
    ADD CONSTRAINT fk_g79te4loyhvkcojrhw4dpjqen FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2706 (class 2606 OID 141639)
-- Name: fk_gi650s6ekhkrl4i9p4pauw9i0; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint
    ADD CONSTRAINT fk_gi650s6ekhkrl4i9p4pauw9i0 FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2812 (class 2606 OID 142169)
-- Name: fk_glpk3fs5rn8mfdt6466fjms4y; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision
    ADD CONSTRAINT fk_glpk3fs5rn8mfdt6466fjms4y FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2793 (class 2606 OID 142074)
-- Name: fk_gm6gm97f113xt6eot6bovu3hx; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY password_reset_token
    ADD CONSTRAINT fk_gm6gm97f113xt6eot6bovu3hx FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2753 (class 2606 OID 141874)
-- Name: fk_golrj9qd2evn6ve7kmgnl32si; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_orgs
    ADD CONSTRAINT fk_golrj9qd2evn6ve7kmgnl32si FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2735 (class 2606 OID 141784)
-- Name: fk_gw2mneuoptxh46opfyk89a7qv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_backup_profile
    ADD CONSTRAINT fk_gw2mneuoptxh46opfyk89a7qv FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2811 (class 2606 OID 142164)
-- Name: fk_gx156dtfas6du3i4k4d17oh0s; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision
    ADD CONSTRAINT fk_gx156dtfas6du3i4k4d17oh0s FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2831 (class 2606 OID 142274)
-- Name: fk_gx6ovu2xjmltgbos0qw61p2sf; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_orgs
    ADD CONSTRAINT fk_gx6ovu2xjmltgbos0qw61p2sf FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2854 (class 2606 OID 142454)
-- Name: fk_gxu4r2xkyrmjrywi9wydseg3d; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_config
    ADD CONSTRAINT fk_gxu4r2xkyrmjrywi9wydseg3d FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2775 (class 2606 OID 141984)
-- Name: fk_gy7pw80kqvumksrgtl885h8dj; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_plugins
    ADD CONSTRAINT fk_gy7pw80kqvumksrgtl885h8dj FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2743 (class 2606 OID 141824)
-- Name: fk_hak9b1vvqrbnduor7ratiwa71; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_plugin_profile
    ADD CONSTRAINT fk_hak9b1vvqrbnduor7ratiwa71 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2738 (class 2606 OID 141799)
-- Name: fk_hfuy9b1ltugu1eg6ne7kfveci; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_commands
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfveci FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2833 (class 2606 OID 142284)
-- Name: fk_hitr4v6cyhpnxfhjp6x5gwg3h; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_user_groups
    ADD CONSTRAINT fk_hitr4v6cyhpnxfhjp6x5gwg3h FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2817 (class 2606 OID 142204)
-- Name: fk_hlmqh5134y7fk885sp9uwmdyr; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_user_groups
    ADD CONSTRAINT fk_hlmqh5134y7fk885sp9uwmdyr FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2845 (class 2606 OID 142344)
-- Name: fk_hprlrsyr7nwby3t8o76585rwb; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_hprlrsyr7nwby3t8o76585rwb FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2789 (class 2606 OID 142054)
-- Name: fk_hqwvs5oofhfb5tdkdgacw36ei; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY message_entitled_users_pks
    ADD CONSTRAINT fk_hqwvs5oofhfb5tdkdgacw36ei FOREIGN KEY (message_id) REFERENCES inbox_message(id);


--
-- TOC entry 2699 (class 2606 OID 141604)
-- Name: fk_i1lxo54ayfxo74ark05nvvkx5; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_rollback_profile_container
    ADD CONSTRAINT fk_i1lxo54ayfxo74ark05nvvkx5 FOREIGN KEY (app_rollback_profile_id) REFERENCES app_rollback_profile(id);


--
-- TOC entry 2801 (class 2606 OID 142114)
-- Name: fk_i39mfav4unjuch428xe3wkbqp; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_users
    ADD CONSTRAINT fk_i39mfav4unjuch428xe3wkbqp FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2844 (class 2606 OID 142339)
-- Name: fk_i564ddj641vg5s0u9m9drs5ei; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_i564ddj641vg5s0u9m9drs5ei FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2850 (class 2606 OID 142369)
-- Name: fk_iciuldwxbg5netu0ds5cqlbqq; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users_user_group_ids
    ADD CONSTRAINT fk_iciuldwxbg5netu0ds5cqlbqq FOREIGN KEY (users_id) REFERENCES users(id);


--
-- TOC entry 2770 (class 2606 OID 141959)
-- Name: fk_id3mauxbn6a1eaq3qx3mn8hrj; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image
    ADD CONSTRAINT fk_id3mauxbn6a1eaq3qx3mn8hrj FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2719 (class 2606 OID 141704)
-- Name: fk_igl5o7l75crycdyrw95dmtyql; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build
    ADD CONSTRAINT fk_igl5o7l75crycdyrw95dmtyql FOREIGN KEY (registry_account_id) REFERENCES registry_account(id);


--
-- TOC entry 2696 (class 2606 OID 141589)
-- Name: fk_im0bgrheig3x37dg0xoryprqv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_plugin_profiles_containers
    ADD CONSTRAINT fk_im0bgrheig3x37dg0xoryprqv FOREIGN KEY (containers_id) REFERENCES container_plugin_profile(id);


--
-- TOC entry 2690 (class 2606 OID 141559)
-- Name: fk_in1ewc69fmv6s2phdk3toke3o; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_backup_profiles_containers
    ADD CONSTRAINT fk_in1ewc69fmv6s2phdk3toke3o FOREIGN KEY (containers_id) REFERENCES container_backup_profile(id);


--
-- TOC entry 2765 (class 2606 OID 141934)
-- Name: fk_ip2g8al5vi1nddkx83mt992kc; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_image
    ADD CONSTRAINT fk_ip2g8al5vi1nddkx83mt992kc FOREIGN KEY (docker_server_id) REFERENCES docker_server(id);


--
-- TOC entry 2713 (class 2606 OID 141674)
-- Name: fk_irfsgf9onjasfuv5oalihoxtd; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_users
    ADD CONSTRAINT fk_irfsgf9onjasfuv5oalihoxtd FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);


--
-- TOC entry 2733 (class 2606 OID 141774)
-- Name: fk_it8lrwmy3n644jj05t10c1rb8; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container
    ADD CONSTRAINT fk_it8lrwmy3n644jj05t10c1rb8 FOREIGN KEY (docker_server_id) REFERENCES docker_server(id);


--
-- TOC entry 2695 (class 2606 OID 141584)
-- Name: fk_j0sc4if161ri5dt5v1vtgeto0; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_plugin_profiles
    ADD CONSTRAINT fk_j0sc4if161ri5dt5v1vtgeto0 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2746 (class 2606 OID 141844)
-- Name: fk_k2iq32py1n7e0mfq2ohqn2qsc; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_plugin_profiles
    ADD CONSTRAINT fk_k2iq32py1n7e0mfq2ohqn2qsc FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2842 (class 2606 OID 142329)
-- Name: fk_k4031w2lg2cpc6jlnb0ocvjje; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT fk_k4031w2lg2cpc6jlnb0ocvjje FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2758 (class 2606 OID 141899)
-- Name: fk_kj59obc29i88pd13kyf9fn6tn; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_users
    ADD CONSTRAINT fk_kj59obc29i88pd13kyf9fn6tn FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2798 (class 2606 OID 142099)
-- Name: fk_kmfy7owve0swfwuul89fm75wc; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_orgs
    ADD CONSTRAINT fk_kmfy7owve0swfwuul89fm75wc FOREIGN KEY (plugin_id) REFERENCES plugin(id);


--
-- TOC entry 2780 (class 2606 OID 142009)
-- Name: fk_l054r12hao1msbpbe8ykj6v0s; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message
    ADD CONSTRAINT fk_l054r12hao1msbpbe8ykj6v0s FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2757 (class 2606 OID 141894)
-- Name: fk_l3c823p6k4bsluv5hfr7fgwjd; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_users
    ADD CONSTRAINT fk_l3c823p6k4bsluv5hfr7fgwjd FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2818 (class 2606 OID 142209)
-- Name: fk_lfbhh6bfhxmy4236shhq0uk5s; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_entitled_user_groups
    ADD CONSTRAINT fk_lfbhh6bfhxmy4236shhq0uk5s FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2810 (class 2606 OID 142159)
-- Name: fk_lkfuqusdg527cxnxs6h2ml5qf; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT fk_lkfuqusdg527cxnxs6h2ml5qf FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2851 (class 2606 OID 142374)
-- Name: fk_lqlmincmr8rucygmp3mnqcw1d; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users_user_groups
    ADD CONSTRAINT fk_lqlmincmr8rucygmp3mnqcw1d FOREIGN KEY (user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2796 (class 2606 OID 142089)
-- Name: fk_lsg9vju7kbn7hlm1s8jxvubx8; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_groups_pks
    ADD CONSTRAINT fk_lsg9vju7kbn7hlm1s8jxvubx8 FOREIGN KEY (plugin_id) REFERENCES plugin(id);


--
-- TOC entry 2808 (class 2606 OID 142149)
-- Name: fk_m5jf4m6lklx71bgrhj4wneb0i; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY port_bindings
    ADD CONSTRAINT fk_m5jf4m6lklx71bgrhj4wneb0i FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2747 (class 2606 OID 141839)
-- Name: fk_m8c7im75q48p07wt7gy6tm47d; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_plugin_profiles
    ADD CONSTRAINT fk_m8c7im75q48p07wt7gy6tm47d FOREIGN KEY (plugin_profiles_id) REFERENCES plugin_profile(id);


--
-- TOC entry 2826 (class 2606 OID 142249)
-- Name: fk_mg41by48acpxbdlhetvi86qpx; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_task_containers
    ADD CONSTRAINT fk_mg41by48acpxbdlhetvi86qpx FOREIGN KEY (provision_task_id) REFERENCES provision_task(id);


--
-- TOC entry 2754 (class 2606 OID 141879)
-- Name: fk_mhxnoakmyoco2kwrl99nolo09; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_orgs
    ADD CONSTRAINT fk_mhxnoakmyoco2kwrl99nolo09 FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2752 (class 2606 OID 141869)
-- Name: fk_mk3c1v3n4iq1ffus0uc8sf9h8; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center_entitled_groups_pks
    ADD CONSTRAINT fk_mk3c1v3n4iq1ffus0uc8sf9h8 FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2721 (class 2606 OID 141714)
-- Name: fk_mxxh69fruys0evpnm9tipvwab; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_build_tasks
    ADD CONSTRAINT fk_mxxh69fruys0evpnm9tipvwab FOREIGN KEY (build_id) REFERENCES build(id);


--
-- TOC entry 2714 (class 2606 OID 141679)
-- Name: fk_my0xtebm9v7cmpidt0fuv4e5i; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_users_pks
    ADD CONSTRAINT fk_my0xtebm9v7cmpidt0fuv4e5i FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);


--
-- TOC entry 2777 (class 2606 OID 141994)
-- Name: fk_n27ptf467a0rfhwnhbfg8yqr6; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_ports
    ADD CONSTRAINT fk_n27ptf467a0rfhwnhbfg8yqr6 FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2734 (class 2606 OID 141779)
-- Name: fk_nbqr32pxnlq5awikkycbwfbv9; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container
    ADD CONSTRAINT fk_nbqr32pxnlq5awikkycbwfbv9 FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2838 (class 2606 OID 142309)
-- Name: fk_nd8gm9q13tncqh0mpkxtx83h9; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY star
    ADD CONSTRAINT fk_nd8gm9q13tncqh0mpkxtx83h9 FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2766 (class 2606 OID 141939)
-- Name: fk_nlpvxldqdfj4fnaeaefmdube9; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_image
    ADD CONSTRAINT fk_nlpvxldqdfj4fnaeaefmdube9 FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2802 (class 2606 OID 142119)
-- Name: fk_nnt9qp4wo1272jh7ovndx4lk4; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_users
    ADD CONSTRAINT fk_nnt9qp4wo1272jh7ovndx4lk4 FOREIGN KEY (plugin_id) REFERENCES plugin(id);


--
-- TOC entry 2710 (class 2606 OID 141659)
-- Name: fk_ns5xldarenv6l43tl7ylhgb2r; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_user_groups
    ADD CONSTRAINT fk_ns5xldarenv6l43tl7ylhgb2r FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2813 (class 2606 OID 142174)
-- Name: fk_nt9s5b72u1pjjwp7t9g8kmilk; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision
    ADD CONSTRAINT fk_nt9s5b72u1pjjwp7t9g8kmilk FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2711 (class 2606 OID 141664)
-- Name: fk_o4bxml0d0b0xrvu01y7rp9ron; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_user_groups
    ADD CONSTRAINT fk_o4bxml0d0b0xrvu01y7rp9ron FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);


--
-- TOC entry 2705 (class 2606 OID 141634)
-- Name: fk_o4r8wqgi4f6ywljodnpex6wtm; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint
    ADD CONSTRAINT fk_o4r8wqgi4f6ywljodnpex6wtm FOREIGN KEY (tenant_id) REFERENCES tenant(id);


--
-- TOC entry 2723 (class 2606 OID 141724)
-- Name: fk_p0dnh7gyx7yp1wdh943720rdu; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_orgs
    ADD CONSTRAINT fk_p0dnh7gyx7yp1wdh943720rdu FOREIGN KEY (entitled_orgs_id) REFERENCES organization(id);


--
-- TOC entry 2806 (class 2606 OID 142139)
-- Name: fk_p4fqahc13qhby7w466agu4ah9; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_profile_envs
    ADD CONSTRAINT fk_p4fqahc13qhby7w466agu4ah9 FOREIGN KEY (plugin_profile_id) REFERENCES plugin_profile(id);


--
-- TOC entry 2762 (class 2606 OID 141919)
-- Name: fk_p90v2euce495ci2w4i5e6pmqh; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server
    ADD CONSTRAINT fk_p90v2euce495ci2w4i5e6pmqh FOREIGN KEY (data_center_id) REFERENCES data_center(id);


--
-- TOC entry 2787 (class 2606 OID 142044)
-- Name: fk_pb2mf305691p2o6t76sfheeap; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY message_action_users
    ADD CONSTRAINT fk_pb2mf305691p2o6t76sfheeap FOREIGN KEY (message_id) REFERENCES inbox_message(id);


--
-- TOC entry 2750 (class 2606 OID 141859)
-- Name: fk_pgjx17d71p136djjlknnqtc7d; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY data_center
    ADD CONSTRAINT fk_pgjx17d71p136djjlknnqtc7d FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2724 (class 2606 OID 141729)
-- Name: fk_q683tr8syo0jnswc5dgkk0arg; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_orgs
    ADD CONSTRAINT fk_q683tr8syo0jnswc5dgkk0arg FOREIGN KEY (build_id) REFERENCES build(id);


--
-- TOC entry 2823 (class 2606 OID 142234)
-- Name: fk_qr6xfs76w3loe83pwbrruihtq; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_provision_tasks
    ADD CONSTRAINT fk_qr6xfs76w3loe83pwbrruihtq FOREIGN KEY (provision_id) REFERENCES provision(id);


--
-- TOC entry 2800 (class 2606 OID 142109)
-- Name: fk_r1gx6328395qvdd73l3gvxuv0; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_user_groups
    ADD CONSTRAINT fk_r1gx6328395qvdd73l3gvxuv0 FOREIGN KEY (plugin_id) REFERENCES plugin(id);


--
-- TOC entry 2726 (class 2606 OID 141739)
-- Name: fk_r4etfkn2be685xu3fv2uunco8; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_user_groups
    ADD CONSTRAINT fk_r4etfkn2be685xu3fv2uunco8 FOREIGN KEY (build_id) REFERENCES build(id);


--
-- TOC entry 2736 (class 2606 OID 141789)
-- Name: fk_r4yl034mfpxdnm88gbh0tqkl4; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_cap_add
    ADD CONSTRAINT fk_r4yl034mfpxdnm88gbh0tqkl4 FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2786 (class 2606 OID 142039)
-- Name: fk_raabk0liybc9f4p743yp95av; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY inbox_message_entitled_users
    ADD CONSTRAINT fk_raabk0liybc9f4p743yp95av FOREIGN KEY (inbox_message_id) REFERENCES inbox_message(id);


--
-- TOC entry 2689 (class 2606 OID 141554)
-- Name: fk_rdhan7ay79nmnbsrrf8xw0grw; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_backup_profiles
    ADD CONSTRAINT fk_rdhan7ay79nmnbsrrf8xw0grw FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2694 (class 2606 OID 141579)
-- Name: fk_rmge002e1o9oyayc8gf4kaefh; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_backups_containers
    ADD CONSTRAINT fk_rmge002e1o9oyayc8gf4kaefh FOREIGN KEY (app_backedup_info_id) REFERENCES app_backups(id);


--
-- TOC entry 2849 (class 2606 OID 142364)
-- Name: fk_rptayl7trfprewc0uljq5tp94; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY users_authorities
    ADD CONSTRAINT fk_rptayl7trfprewc0uljq5tp94 FOREIGN KEY (users_id) REFERENCES users(id);


--
-- TOC entry 2693 (class 2606 OID 141574)
-- Name: fk_rt9bcqrd1gfngmxbwuh9n0jad; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_backups_containers
    ADD CONSTRAINT fk_rt9bcqrd1gfngmxbwuh9n0jad FOREIGN KEY (containers_id) REFERENCES container_backup_profile(id);


--
-- TOC entry 2776 (class 2606 OID 141989)
-- Name: fk_s50111ptmi7q23ys9n149e7ac; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY image_port_bindings
    ADD CONSTRAINT fk_s50111ptmi7q23ys9n149e7ac FOREIGN KEY (image_id) REFERENCES image(id);


--
-- TOC entry 2709 (class 2606 OID 141654)
-- Name: fk_sfmxc8jqjcxge7iww9o4cqtp3; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY blueprint_entitled_orgs
    ADD CONSTRAINT fk_sfmxc8jqjcxge7iww9o4cqtp3 FOREIGN KEY (blueprint_id) REFERENCES blueprint(id);


--
-- TOC entry 2749 (class 2606 OID 141854)
-- Name: fk_souuiwblm2s4rrtwet2x9nrk3; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_volumes
    ADD CONSTRAINT fk_souuiwblm2s4rrtwet2x9nrk3 FOREIGN KEY (container_id) REFERENCES container(id);


--
-- TOC entry 2744 (class 2606 OID 141829)
-- Name: fk_squls3wkc0klvub8tvcbue1lb; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY container_plugin_profile_plugin_profiles
    ADD CONSTRAINT fk_squls3wkc0klvub8tvcbue1lb FOREIGN KEY (plugin_profiles_id) REFERENCES plugin_profile(id);


--
-- TOC entry 2824 (class 2606 OID 142239)
-- Name: fk_srec0jta8o6m5kvjx5gfboqau; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY provision_task
    ADD CONSTRAINT fk_srec0jta8o6m5kvjx5gfboqau FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2725 (class 2606 OID 141734)
-- Name: fk_ssuv9m1c8ene7s6krj1bg9ym2; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY build_entitled_user_groups
    ADD CONSTRAINT fk_ssuv9m1c8ene7s6krj1bg9ym2 FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2769 (class 2606 OID 141954)
-- Name: fk_t2jb9h1th3bd6rt505kstyyux; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_task
    ADD CONSTRAINT fk_t2jb9h1th3bd6rt505kstyyux FOREIGN KEY (docker_server_id) REFERENCES docker_server(id);


--
-- TOC entry 2834 (class 2606 OID 142289)
-- Name: fk_t4wv82opyw7sf83gfcmgonqvv; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY registry_account_entitled_users
    ADD CONSTRAINT fk_t4wv82opyw7sf83gfcmgonqvv FOREIGN KEY (entitled_users_id) REFERENCES users(id);


--
-- TOC entry 2799 (class 2606 OID 142104)
-- Name: fk_t8gcmtwhndu6dwwjymupt1nlw; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY plugin_entitled_user_groups
    ADD CONSTRAINT fk_t8gcmtwhndu6dwwjymupt1nlw FOREIGN KEY (entitled_user_groups_id) REFERENCES user_group(id);


--
-- TOC entry 2701 (class 2606 OID 141614)
-- Name: fk_tddmd2pnf9yfuwc5bsygts1gb; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY app_scale_in_profiles
    ADD CONSTRAINT fk_tddmd2pnf9yfuwc5bsygts1gb FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2840 (class 2606 OID 142319)
-- Name: fk_to7c5ky7qithh8j5rnwvmgcwt; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY tenant
    ADD CONSTRAINT fk_to7c5ky7qithh8j5rnwvmgcwt FOREIGN KEY (owner_id) REFERENCES users(id);


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

-- REVOKE ALL ON SCHEMA public FROM PUBLIC;
-- REVOKE ALL ON SCHEMA public FROM postgres;
-- GRANT ALL ON SCHEMA public TO postgres;
-- GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-09-14 14:19:46

--
-- PostgreSQL database dump complete
--

