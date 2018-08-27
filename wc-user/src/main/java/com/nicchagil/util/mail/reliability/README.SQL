CREATE TABLE `t_message_send_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型，如邮件、短信',
  `recipients` varchar(1024) DEFAULT NULL COMMENT '收件人',
  `ccs` varchar(1024) DEFAULT NULL COMMENT '抄送人',
  `title` varchar(1024) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `tries` int(11) DEFAULT NULL COMMENT '重试次数',
  `triesLimit` int(11) DEFAULT NULL COMMENT '重试尝试次数限制',
  `exception_msg` varchar(2048) DEFAULT NULL COMMENT '异常信息',
  `status` varchar(32) DEFAULT NULL COMMENT '发送状态，如待发送、已发送、发送失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;