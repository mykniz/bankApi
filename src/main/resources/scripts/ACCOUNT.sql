DROP TABLE IF EXISTS ACCOUNT;

CREATE TABLE IF NOT EXISTS ACCOUNT
(
    account_id   INTEGER      not null AUTO_INCREMENT,
    account VARCHAR(255) not null,
    balance DECIMAL(13,2),
    is_open BOOL,
    user_id int,
    PRIMARY KEY (account_id),
    UNIQUE (account),
    foreign key (user_id) references USER(user_id)
);

insert into ACCOUNT (account, balance, is_open, user_id) values ('64419815581464276017', 1000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, user_id) values ('66546345908858348082', 2000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, user_id) values ('11857403379971070232', 3000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, user_id) values ('11469926864721322302', 4000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, user_id) values ('72337103411062027578', 5000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, user_id) values ('82794100317667558707', 6000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, user_id) values ('03217462696778617732', 0.00, true, 2);
insert into ACCOUNT (account, balance, is_open, user_id) values ('59485826106631327503', 62275005.67, true, 3);
insert into ACCOUNT (account, balance, is_open, user_id) values ('69756317540546747828', 370843929.94, true, 3);
insert into ACCOUNT (account, balance, is_open, user_id) values ('92396319335214821062', 888979325.03, true, 4);
insert into ACCOUNT (account, balance, is_open, user_id) values ('26871899546881238618', 18676903.44, true, 4);
insert into ACCOUNT (account, balance, is_open, user_id) values ('03738235348877083307', 326115792.93, true, 4);
insert into ACCOUNT (account, balance, is_open, user_id) values ('44735277474793129407', 667639623.21, true, 5);
insert into ACCOUNT (account, balance, is_open, user_id) values ('03505702675567495137', 658893773.88, true, 6);
insert into ACCOUNT (account, balance, is_open, user_id) values ('51021770383322204034', 390749416.5, true, 7);
insert into ACCOUNT (account, balance, is_open, user_id) values ('44426978567892851558', 441189721.6, true, 8);
insert into ACCOUNT (account, balance, is_open, user_id) values ('70229680362404915510', 564025474.1, true, 8);
insert into ACCOUNT (account, balance, is_open, user_id) values ('95806003090583058450', 221048738.29, true, 9);
insert into ACCOUNT (account, balance, is_open, user_id) values ('76891833833681189543', 346242211.79, true, 10);
insert into ACCOUNT (account, balance, is_open, user_id) values ('22836292193192463372', 256552024.49, true, 10);


-- insert into ACCOUNT (account, balance, is_open, user_id) values ('01314976742388204630', 193626660.46, true, 9);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('81888352153039819485', 10805335.22, true, 39);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('81297913761580591863', 289718300.07, true, 45);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('06275640596421099799', 339863119.2, true, 40);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('02176975835062095999', 256749668.97, true, 13);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('77794665415893857969', 329955117.6, true, 41);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('99472823092329036418', 103119684.13, true, 22);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('60089818294075685995', 821496758.04, true, 10);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('85375731357566651415', 871972957.48, true, 14);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('09440229397400360299', 299139120.17, true, 22);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('38686274525113203866', 708044631.89, true, 34);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('35420777277566405932', 858872644.85, true, 21);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('07228133793444299264', 647701990.86, true, 41);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('57975513601694370631', 41688529.08, true, 39);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('66660485591979567630', 59110052.96, true, 31);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('78852086930743036858', 324235424.24, true, 40);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('88822767922581839517', 620460324.13, true, 17);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('05351769827999272665', 313166764.19, true, 25);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('13408679424392468562', 629655313.06, true, 29);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('54779385920566844607', 419793842.91, true, 40);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('09928814064124241871', 551040034.72, true, 48);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('26398928908205642073', 66548737.41, true, 42);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('28870293145847160918', 910508964.72, true, 21);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('22687257399963534132', 25419195.2, true, 26);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('49166967377505992254', 909121813.47, true, 5);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('67077758735501577299', 550271269.07, true, 1);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('40485514729095137036', 317325011.81, true, 1);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('08443265340086756502', 909214890.25, true, 15);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('47567052451286252796', 871260779.4, true, 47);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('91925117740020385538', 264359273.6, true, 11);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('31989954086129228922', 607900401.6, true, 49);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('59843522770576878741', 622189784.77, true, 20);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('44303423219479181592', 726922186.61, true, 45);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('41017604200718580449', 27683883.82, true, 24);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('00465243970604620634', 918262902.5, true, 40);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('34239717307774472679', 920645846.79, true, 22);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('45265457672206577076', 493021421.28, true, 29);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('90036315558901351958', 790795582.74, true, 47);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('06019565240630820149', 308992081.04, true, 5);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('22526962937597117977', 600338918.28, true, 23);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('41949782185452850085', 424252140.12, true, 5);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('78406708447635217834', 189491080.21, true, 30);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('24669637559962343934', 647111198.49, true, 13);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('10856707761372255396', 466963956.01, true, 10);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('49085472725479674264', 658393755.14, true, 34);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('58178775263326539568', 659772626.46, true, 37);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('31869510822763606571', 491072523.88, true, 45);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('36632271243325618034', 498946754.71, true, 15);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('38733446022382549517', 545450726.47, true, 41);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('02721498345245384221', 371616792.05, true, 6);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('27257775609773521147', 178747926.47, true, 15);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('38362558145798278555', 817772885.09, true, 18);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('17719687935735014021', 36916513.79, true, 19);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('27933353623096534320', 474098301.17, true, 16);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('68008564325512464892', 822115088.74, true, 28);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('13281108893552729623', 637766509.94, true, 22);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('96984137959423027915', 170457727.5, true, 42);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('41518530420632112578', 856007712.01, true, 20);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('64114153412872371632', 87119577.44, true, 37);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('17749641587259446418', 798584013.71, true, 25);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('57221486104541034358', 336440987.35, true, 11);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('92260510434126246542', 919174298.15, true, 2);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('98417666650189689567', 774000206.24, true, 23);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('88078641451450873595', 167301277.79, true, 21);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('76603662852568243107', 815041732.79, true, 43);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('17536776387945969837', 339945272.17, true, 33);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('43052143277516941600', 777677527.42, true, 44);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('49027323656908214648', 941368719.76, true, 9);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('99483219556336910087', 40444747.07, true, 34);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('57737192719161420779', 347186515.46, true, 17);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('39115439799220735755', 837892773.49, true, 12);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('52263307636090827197', 455923844.64, true, 23);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('66226160788076447303', 31987077.22, true, 29);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('95068925075168596288', 103058717.56, true, 33);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('24084267685342486778', 984519727.94, true, 28);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('92903413701867588414', 962901983.71, true, 39);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('92350080583350020580', 73454819.23, true, 29);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('20106309855000220139', 344170421.8, true, 15);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('17212105775902884564', 984571829.97, true, 48);
-- insert into ACCOUNT (account, balance, is_open, user_id) values ('87769024936679591370', 9563072.0, true, 2);
