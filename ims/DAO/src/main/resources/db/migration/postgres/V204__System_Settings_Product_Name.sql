-- Change HyperForm to HyperCloud.

update app_config set key_ = 'HyperCloud.edition' where id like 'hyperForm.edition';
update app_config set key_ = 'HyperCloud.version' where id like 'hyperForm.version';
