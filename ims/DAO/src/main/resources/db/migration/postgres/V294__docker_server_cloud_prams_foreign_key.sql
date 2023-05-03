--
-- Name: fk_7b6kmbxx7lpljhp75g7wkbx09; Type: FK CONSTRAINT; Schema: public; Owner: dchq
--

ALTER TABLE ONLY docker_server_cloud_params
    ADD CONSTRAINT fk_9a5jqdsx7lpljhp75g7wkbx09 FOREIGN KEY (docker_server_id) REFERENCES docker_server(id);