CREATE DATABASE IF NOT EXISTS Main

CREATE TABLE IF NOT EXISTS usuario (id INT AUTO_INCREMENT,  nombre VARCHAR(255), password VARCHAR(255), provincia VARCHAR(255), municipio VARCHAR(255), bloqueado BOOLEAN, PRIMARY KEY(id) )

INSERT INTO `preguntas`(`id`, `question`, `answer1`, `answer2`, `answer3`, `correct`) VALUES (1, admin , FJvbBAgawTxMtIB5VDqHkjtKiQZfQ1GMCSRYo/KxWhO7qnaneh9XQNC6RXbSliRt, Burgos, Aranda de Duero , false)

INSERT INTO `preguntas`(`id`, `question`, `answer1`, `answer2`, `answer3`, `correct`) VALUES (2, Hector, s36nC/aBzmKD+RocODJZKpLuNG42rLqG1oxXjBM1TDyuleowG7YDK15ALsOPi9Wv, Burgos, Aranda de Duero, false)


