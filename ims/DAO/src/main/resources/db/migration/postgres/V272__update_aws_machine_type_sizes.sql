-- Author: fmohammad@hypergrid.com

update machine_type set vcpu = '1' , mem='0.613' , disk= 'EBS only', total_disk = null where cloud = 'AWS_EC2' and machine_id = 't1.micro' ;
update machine_type set vcpu = '1' , mem='1.7' , disk= '1 x 160', total_disk = 160 where cloud = 'AWS_EC2' and machine_id = 'm1.small' ;
update machine_type set vcpu = '1' , mem='3.7' , disk= '1 x 410', total_disk = 410 where cloud = 'AWS_EC2' and machine_id = 'm1.medium' ;
update machine_type set vcpu = '2' , mem='7.5' , disk= '2 x 420', total_disk = 840 where cloud = 'AWS_EC2' and machine_id = 'm1.large' ;
update machine_type set vcpu = '4' , mem='15' , disk= '4 x 420', total_disk = null where cloud = 'AWS_EC2' and machine_id = 'm1.xlarge' ;
update machine_type set vcpu = '2' , mem='1.7' , disk= '1 x 350', total_disk = 350 where cloud = 'AWS_EC2' and machine_id = 'c1.medium' ;
update machine_type set vcpu = '8' , mem='7' , disk= '4 x 420', total_disk = null where cloud = 'AWS_EC2' and machine_id = 'c1.xlarge' ;
update machine_type set vcpu = '8' , mem='15' , disk= '1 x 60 (SSD)', total_disk = 60 where cloud = 'AWS_EC2' and machine_id = 'g2.2xlarge' ;
update machine_type set vcpu = '2' , mem='17.1' , disk= '1 x 420', total_disk = 420 where cloud = 'AWS_EC2' and machine_id = 'm2.xlarge' ;
update machine_type set vcpu = '4' , mem='34.2' , disk= '1 x 850', total_disk = 850 where cloud = 'AWS_EC2' and machine_id = 'm2.2xlarge' ;
update machine_type set vcpu = '8' , mem='68.4' , disk= '2 x 840', total_disk = null where cloud = 'AWS_EC2' and machine_id = 'm2.4xlarge' ;
update machine_type set vcpu = '4' , mem='30.5' , disk= '1 x 800 (SSD)', total_disk = 800 where cloud = 'AWS_EC2' and machine_id = 'i2.xlarge' ;
update machine_type set vcpu = '8' , mem='61' , disk= '2 x 800 (SSD)', total_disk = null where cloud = 'AWS_EC2' and machine_id = 'i2.2xlarge' ;
update machine_type set vcpu = '16' , mem='122' , disk= '4 x 800 (SSD)', total_disk = null where cloud = 'AWS_EC2' and machine_id = 'i2.4xlarge' ;
update machine_type set vcpu = '32' , mem='244' , disk= '8 x 800 (SSD)', total_disk = null where cloud = 'AWS_EC2' and machine_id = 'i2.8xlarge' ;

delete from machine_type where cloud = 'AWS_EC2' and machine_id IN ('hs1.8xlarge' , 'hi1.4xlarge', 'cc1.4xlarge', 'cg1.4xlarge', 'cc2.8xlarge', 'd2.xlarge','d2.2xlarge','d2.4xlarge' ,'d2.8xlarge');

