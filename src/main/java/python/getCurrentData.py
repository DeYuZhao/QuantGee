# # -*- coding:utf-8 -*-
# from sqlalchemy import create_engine
# import tushare as ts
# import time
# import MySQLdb
# import sys
#
# reload(sys)
# sys.setdefaultencoding('utf-8')
#
# list = ['000001', '000002', '000004', '000005', '000006', '000007', '000008', '000009', '000010', '000011', '000012',
#         '000014', '000016', '000018', '000019', '000020', '000021', '000022', '000023', '000025', '000026', '000027',
#         '000028', '000029', '000030', '000031', '000032', '000034', '000035', '000036', '000039',
#         '000040', '000042', '000043', '000046', '000048', '000049', '000050', '000055', '000056', '000058',
#         '000059', '000060', '000061','000063', '000065', '000066', '000068', '000069', '000070', '000078',
#         '000088', '000089', '000090', '000096', '000099', '000100', '000151', '000153', '000156',
#         '000157', '000158', '000159', '000301', '000333', '000338', '000400', '000401', '000402', '000403',
#         '000404', '000407', '000408', '000409', '000410', '000411', '000413', '000415', '000416', '000417', '000419',
#         '000420', '000421', '000422', '000423', '000425', '000426','000429', '000430', '000488',
#         '000498', '000501', '000505', '000506', '000507', '000509', '000510', '000511',
#         '000513', '000514', '000516', '000517', '000518', '000519', '000521', '000523', '000524', '000525',
#         '000526', '000528', '000529', '000530', '000531', '000532', '000533', '000534', '000536', '000537', '000538',
#         '000539', '000540', '000541', '000543', '000544', '000545', '000546', '000548', '000550', '000551',
#         '000552', '000553', '000554', '000565', '000566', '000567', '000568', '000570', '000571', '000572', '000573', '000576', '000581', '000582',
#         '000584', '000585', '000586', '000587', '000589', '000590', '000591', '000592', '000593', '000595', '000596',
#         '000597', '000598', '000599', '000600', '000601', '000603', '000605', '000606', '000607', '000608', '000609',
#         '000610', '000611', '000616', '000617', '000619', '000620', '000622', '000623',
#         '000625', '000626', '000627', '000628', '000629', '000630', '000631', '000632', '000633', '000635', '000636',
#         '000637', '000638', '000639', '000650', '000651', '000652', '000655', '000656', '000657', '000659', '000661',
#         '000662', '000663', '000665', '000666', '000667', '000668', '000669', '000670', '000671', '000672', '000673',
#         '000676', '000677', '000678', '000679', '000680', '000681', '000682', '000683', '000685', '000686', '000687',
#         '000690', '000691', '000692', '000720', '000721', '000722', '000723', '000725', '000726', '000727', '000728', '000729', '000731',
#         '000732', '000733', '000735', '000736', '000737', '000738', '000739', '000748', '000750', '000752',
#         '000753', '000755', '000756', '000757', '000758', '000759', '000766', '000767', '000776', '000777',
#         '000778', '000779', '000780', '000782', '000783', '000785', '000786', '000809', '000810', '000811', '000812', '000813', '000815',
#         '000816', '000818', '000819', '000822', '000823', '000825', '000826', '000828', '000829',
#         '000830', '000831', '000835', '000836', '000837', '000838', '000839', '000848', '000850', '000851',
#         '000852', '000856', '000858', '000859', '000860', '000861', '000862', '000863',  '000880', '000881',
#         '000882', '000883', '000885', '000886', '000887', '000888', '000895', '000897', '000898', '000899', '000900', '000901', '000902',
#         '000903', '000905', '000906', '000908', '000909', '000910', '000911', '000912', '000913', '000915', '000916',
#         '000917', '000918', '000919', '000920', '000921', '000922', '000923', '000996', '000997', '000998', '000999', '001696', '001896', '002001',
#         '002002', '002003', '002004', '002005', '002006', '002007', '002008', '002017', '002018', '002019', '002020', '002021', '002022', '002023',
#         '002024', '002025', '002026', '002027', '002028', '002029', '002030', '002031', '002032', '002033', '002034',
#         '002035', '002036', '002037', '002038', '002039', '002040', '002041', '002042', '002043', '002044', '002045',
#         '002046', '002047', '002048', '002049', '002050', '002051', '002052', '002053', '002054', '002055', '002056',
#         '002057', '002058', '002059', '002060', '002061', '002062', '002075', '002076', '002077', '002078',
#         '002079', '002080', '002081', '002082', '002083', '002084', '002085', '002086', '002087', '002088', '002089',
#         '002090', '002091', '002093', '002094', '002095', '002096', '002097', '002098', '002099', '002100',
#         '002101', '002132', '002133', '002134', '002135',
#         '002136', '002137', '002138', '002139', '002140', '002141', '002142', '002143', '002144', '002145', '002146',
#         '002147', '002152', '002153', '002154', '002155', '002156', '002157',
#         '002158', '002159', '002160', '002161', '002162', '002163', '002164', '002165', '002166', '002168',
#         '002169', '002170', '002171', '002172', '002173', '002174', '002175', '002176', '002177', '002178', '002179',
#         '002180', '002181', '002182', '002183', '002184', '002185', '002186', '002187', '002188', '002189', '002190',
#         '002191', '002193', '002194', '002195', '002196', '002197', '002198', '002199', '002200', '002201',
#         '002202', '002203', '002204', '002205', '002206', '002207', '002208', '002209', '002210', '002211', '002212',
#         '002213', '002214', '002215', '002216', '002217', '002218', '002219', '002220', '002221', '002222', '002223',
#         '002224', '002226', '002227', '002228', '002229', '002230', '002231', '002232', '002233', '002234',
#         '002235', '002236', '002237', '002238', '002239', '002240', '002241', '002242', '002243', '002244', '002245',
#         '002246', '002247', '002248', '002249', '002250', '002251', '002252', '002253', '002254', '002255', '002256',
#         '002258', '002259', '002260', '002261', '002262', '002264', '002265', '002266', '002267', '002268',
#         '002269', '002270', '002271', '002272', '002273', '002274', '002275', '002276', '002277', '002278', '002279',
#         '002280', '002281', '002282', '002283', '002284', '002285', '002286', '002287', '002288', '002289', '002290',
#         '002291', '002292', '002293', '002294', '002295', '002296', '002307', '002308', '002309', '002310', '002311', '002312', '002313',
#         '002314', '002315', '002316', '002317', '002318', '002319', '002320', '002321', '002322', '002323', '002324',
#         '002325', '002326', '002327', '002328', '002329', '002330', '002331', '002332', '002333', '002334', '002335',
#         '002336', '002337', '002338', '002339', '002340', '002341', '002342', '002343', '002344', '002345', '002346',
#         '002347', '002349', '002350', '002351', '002352', '002353', '002354', '002355', '002356', '002357', '002358',
#         '002359', '002360', '002361', '002362', '002363', '002364', '002365', '002375', '002376', '002377', '002378', '002379', '002380',
#         '002381', '002382', '002383', '002384', '002385', '002386', '002387', '002388', '002389', '002390', '002391',
#         '002392', '002393', '002394', '002395', '002396', '002397', '002398', '002399', '002400', '002401', '002402',
#         '002403', '002404', '002405', '002406', '002468',
#         '002469', '002470', '002471', '002472', '002473', '002474', '002475', '002476', '002477', '002478', '002479',
#         '002480', '002481', '002482', '002483', '002484', '002485', '002486', '002487', '002488', '002489', '002490',
#         '002491', '002492', '002493', '002494', '002495', '002496', '002497', '002498', '002499', '002500', '002501',
#         '002503', '002504', '002505', '002507', '002508', '002509', '002526', '002527', '002528', '002529', '002530', '002531', '002532', '002533', '002534', '002535', '002536',
#         '002537', '002538', '002539', '002540', '002541', '002542', '002543', '002544', '002545', '002546', '002547',
#         '002548', '002549', '002550', '002551', '002552', '002553', '002554', '002572', '002573', '002574', '002576',
#         '002577', '002578', '002579', '002580', '002581', '002583', '002584', '002585', '002586', '002587', '002588', '002589', '002590', '002591', '002592',
#         '002593', '002594', '002595', '002596', '002597', '002598', '002599', '002600', '002601', '002602', '002603',
#         '002604', '002606', '002607', '002608', '002609', '002611', '002612', '002613', '002614', '002615', '002616',
#         '002617', '002618', '002619', '002620', '002621', '002622', '002623', '002641', '002642', '002643', '002644', '002645', '002646', '002647', '002648', '002649',
#         '002650', '002651', '002652', '002653', '002654', '002655', '002656', '002657', '002658', '002659', '002660',
#         '002661', '002662', '002663', '002664', '002665', '002666', '002667', '002668', '002669', '002670', '002671',
#         '002672', '002673', '002674', '002675', '002676', '002677', '002679', '002680', '002681', '002682', '002683',
#         '002684', '002685', '002686', '002687', '002688', '002689', '002690', '002691', '002692', '002693', '002694',
#         '002695', '002696', '002697', '002698', '002700', '002701', '002702', '002703', '002705', '002706', '002707',
#         '002708', '002709', '002711', '002712', '002713', '002714', '002715', '002717', '002718', '002719', '002722',
#         '002723', '200011', '200012', '200016',
#         '200017', '200018', '200019', '200020', '200022', '200025', '200026', '200028', '200029', '200030',
#         '200429', '200468', '200488', '200505', '200512', '200521', '200530', '200539', '200541', '200550', '200553',
#         '200570', '200581', '300001', '300002', '300010', '300011', '300012', '300013', '300014', '300015', '300016', '300017', '300018', '300019', '300020',
#         '300021', '300022', '300023', '300024', '300025', '300026', '300027', '300028', '300030', '300031',
#         '300032', '300033', '300034', '300035', '300036', '300037', '300038', '300039', '300040', '300041', '300042',
#         '300044', '300045', '300046', '300047', '300048', '300049', '300050', '300051', '300052', '300053', '300054',
#         '300055', '300056', '300057', '300058', '300059', '300061', '300062', '300063', '300064', '300065', '300066',
#         '300067', '300068', '300069', '300070',  '300072', '300073', '300074', '300075', '300076', '300077',
#         '300078', '300079', '300080', '300081', '300082', '300083', '300111', '300112', '300113', '300114', '300115', '300116', '300117', '300118', '300119', '300120', '300121',
#         '300122', '300123', '300124', '300125', '300126', '300127', '300128', '300129', '300130', '300131', '300132',
#         '300133', '300134', '300135', '300136', '300137', '300138', '300139', '300140', '300141', '300142', '300143',
#         '300144', '300145', '300146', '300147', '300148', '300149', '300150', '300151', '300152', '300153', '300154',
#         '300155', '300156', '300157', '300158', '300159', '300171', '300172', '300173', '300174', '300175', '300176',
#         '300177', '300178', '300179', '300180', '300181',  '300183', '300184', '300185', '300187', '300188',
#         '300189', '300190', '300191', '300192', '300193', '300194', '300195',
#         '300200', '300201', '300202', '300203', '300204', '300205', '300206', '300207', '300208', '300209', '300210',
#         '300211',  '300220', '300221',
#         '300222', '300223', '300224', '300225', '300226', '300227', '300228', '300229', '300230', '300231', '300232',
#         '300233', '300234', '300235', '300236', '300237', '300238', '300239', '300240', '300241', '300242', '300243',
#         '300244', '300245', '300246', '300252', '300253', '300254',
#         '300255', '300256', '300257', '300258', '300259', '300260', '300261', '300262', '300263', '300264', '300265',
#         '300266', '300267','300269', '300270', '300271', '300272', '300273', '300274', '300275', '300276',
#         '300277', '300278',  '300338', '300339', '300340', '300341', '300342', '300343', '300344', '300345',
#         '300346', '300347','300348', '300349', '300350', '300351', '300352', '300353', '300354', '300355','600000', '600004', '600006', '600007', '600008', '600009',
#         '600010', '600011', '600012', '600015', '600016', '600017', '600018', '600019', '600020', '600021', '600022',
#         '600023', '600026', '600027', '600028', '600029', '600030', '600031', '600033', '600035', '600036', '600037',
#         '600038', '600039', '600048', '600050', '600051', '600052', '600053', '600054', '600056', '600057',
#         '600058', '600059', '600060', '600061', '600062', '600063', '600064', '600066', '600067', '600068', '600069',
#         '600070', '600072', '600073', '600076', '600077', '600078', '600079', '600080', '600081',
#         '600082', '600083',  '600085', '600086', '600088', '600089', '600090', '600091', '600093', '600094',
#         '600095', '600096', '600097', '600098', '600099', '600100', '600101', '600103', '600104', '600105', '600106',
#         '600107', '600108', '600109', '600110', '600111', '600112', '600113', '600114', '600115', '600116', '600117',
#         '600118', '600119', '600120', '600121', '600122', '600123', '600125', '600126',  '600145', '600146', '600148',
#         '600149', '600150', '600151', '600152', '600153', '600155', '600156',
#         '600159', '600160', '600161', '600162', '600163', '600165', '600166', '600167', '600168', '600169', '600170',
#         '600171', '600172', '600173', '600175', '600176', '600177', '600178', '600179', '600180', '600182', '600183',
#         '600184', '600186', '600187', '600188', '600189', '600190', '600191', '600192', '600197', '600198', '600199',
#         '600200', '600201', '600202', '600215', '600216', '600217', '600218', '600219', '600220','600256', '600257', '600258',
#         '600259', '600260', '600261', '600262', '600265', '600266', '600267', '600268', '600269', '600270', '600271',
#         '600272', '600273', '600275', '600276', '600277', '600278', '600279', '600280', '600281', '600283',
#         '600284', '600285', '600287', '600288', '600289', '600290', '600291', '600292',  '600307', '600308', '600309',
#         '600310', '600311', '600312', '600313', '600315', '600316', '600317', '600318', '600319', '600320', '600321',
#         '600322', '600323', '600325', '600326', '600327', '600328', '600329', '600330', '600331', '600332', '600333',
#         '600335', '600336', '600337', '600338', '600339', '600340', '600343', '600345', '600348', '600350','600351', '600352', '600353', '600354', '600355', '600356', '600358', '600359', '600360', '600361', '600362',
#         '600363', '600365', '600366', '600367', '600368', '600369', '600370', '600371', '600372', '600382', '600383', '600386', '600387', '600388',
#         '600389', '600390', '600391', '600393', '600395', '600396', '600397', '600398', '600399', '600400', '600401',
#         '600403', '600405', '600406', '600408', '600409', '600410', '600415', '600416', '600419', '600420',
#         '600421', '600422', '600423', '600425', '600426', '600428', '600429', '600432', '600433', '600435', '600436',
#         '600438', '600439', '600444', '600446', '600448', '600449', '600452', '600455', '600456', '600458', '600459',
#         '600460', '600461', '600462', '600463', '600466', '600467', '600468', '600469', '600470', '600475', '600476',
#         '600477',  '600491', '600493', '600495', '600496', '600497', '600498', '600499', '600500', '600501',
#         '600502', '600503', '600505', '600506', '600507', '600508', '600509', '600510', '600511', '600512', '600513',
#         '600515', '600516', '600517', '600518', '600519',  '600521', '600522', '600523', '600525', '600526',
#         '600527', '600528', '600529', '600530', '600531', '600532', '600533', '600535', '600536', '600537', '600538',
#         '600539', '600540', '600543', '600545', '600546', '600547', '600548', '600549', '600550', '600551', '600552',
#         '600555', '600557', '600558', '600559', '600560', '600561', '600562', '600563', '600565', '600566',
#         '600567', '600568', '600569', '600570', '600571', '600572', '600573', '600575', '600577', '600578',
#         '600579', '600580', '600581', '600582', '600583', '600584', '600585', '600586', '600587', '600588', '600589',
#         '600590', '600592', '600593', '600594', '600595', '600596', '600597', '600598', '600599', '600600', '600616'
#                                                                                                             '600618',
#         '600619', '600620', '600621', '600622', '600623', '600624',
#         '600626', '600628',
#         '600635', '600636', '600637', '600638', '600639',
#         '600640', '600641', '600642', '600643', '600644', '600645',  '600654', '600655',  '600657', '600658', '600660', '600662', '600663', '600664',
#         '600665',  '600686', '600687', '600688', '600689', '600690',
#         '600691', '600692', '600693', '600694', '600695', '600696', '600697', '600698', '600699', '600701', '600702',
#         '600703', '600704', '600705', '600706', '600707', '600710', '600711', '600712', '600713', '600714',
#         '600715', '600716', '600717', '600718', '600719', '600720', '600721', '600722', '600723', '600724', '600725',
#         '600726', '600727', '600728', '600729', '600731', '600732', '600733', '600734', '600735', '600736',
#         '600737', '600738', '600739', '600740', '600741', '600742', '600743', '600744', '600745', '600746', '600747',
#         '600748', '600749', '600750', '600751',   '600760', '600761', '600763', '600764', '600765', '600767', '600768', '600769', '600770', '600771', '600773',
#         '600774', '600794', '600795', '600796', '600797',
#         '600798', '600800', '600801', '600802', '600803', '600804', '600805', '600806', '600807', '600808', '600809',
#         '600810', '600811', '600812', '600814', '600815', '600816', '600820', '600821', '600822',
#         '600823', '600824', '600825', '600826', '600827', '600828', '600829', '600830', '600831', '600833', '600834',
#         '600835', '600836', '600837', '600838', '600839', '600841', '600843', '600844', '600845', '600846', '600847'
#     ,'600851', '600853', '600854', '600855', '600856', '600857', '600858', '600859', '600860',
#         '600861','600865', '600866', '600867', '600868', '600869', '600870', '600871',
#         '600872', '600873', '600874', '600875', '600876', '600877', '600879', '600880', '600881', '600882', '600883',
#         '600884', '600885', '600886', '600887', '600888', '600890', '600891', '600892', '600893', '600894',
#         '600895', '600896', '600897', '600898', '600900', '601000', '601001', '601002', '601003', '601005', '601006',
#         '601007', '601008', '601009', '601010', '601011', '601012', '601028',
#         '601038', '601058', '601088', '601098', '601099', '601100', '601101', '601106', '601107', '601111', '601113',
#         '601116', '601117', '601118', '601137', '601139', '601158', '601166', '601168', '601169', '601177',
#         '601179', '601186', '601188', '601208', '601216', '601218', '601222', '601225', '601231', '601233', '601238', '601258', '601288', '601311', '601313', '601318', '601328',
#         '601333', '601336', '601339',  '601369', '601377', '601390', '601398', '601515', '601518', '601519',
#         '601555', '601558', '601566', '601567',  '601588', '601599', '601600', '601601', '601607', '601608',
#         '601616', '601618', '601628', '601633', '601636', '601666', '601668', '601669', '601677', '601678', '601688','601700', '601717', '601718', '601727', '601766', '601777', '601788', '601789', '601798',
#         '601799', '601800', '601801', '601808', '601818', '601857', '601866', '601872', '601877', '601880', '601886',
#         '601888', '601890', '601898', '601899', '601901', '601908', '601918', '601919', '601928', '601929', '601933',
#         '601939', '601988', '601989', '601991', '601992', '601996', '601998',
#         '601999', '603000', '603001', '603002', '603003', '603005', 'sh', 'sz', 'hs300', 'sz50', 'zxb', 'cyb']
#
#
# def getStockInfo(code, start):
#     # df = ts.get_hist_data(code=code, start='2017-06-03')
#     # df['stockID'] = code
#     # print code
#     # engine = create_engine('mysql+pymysql://root:19961112@localhost:3306/quantgee_data?charset=utf8')
#     # df.to_sql('Stock', engine, if_exists='append')
#     #
#     # return
#     str = ''
#     df0 = ts.get_hist_data(code=code,start=start)
#     try:
#         conn = MySQLdb.connect(host='127.0.0.1', port=3306, user='root', passwd='19961112',
#                                db='quantgee_data', charset='utf8')
#         cur = conn.cursor()
#         for n in range(0, len(df0)):
#             s = df0.iloc[n]
#             SQL = "Insert into Stock (date, open, high, close, low, volume, price_change, p_change, ma5, ma10 , ma20" \
#                   ", v_ma5, v_ma10, v_ma20, turnover, stockID) Values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
#             l = (s['date'], s['open'], s['high'], s['close'], s['low'], s['volume'], s['price_change'], s['p_change'],
#                  s['ma5'], s['ma10'], s['ma20'], s['v_ma5'], s['v_ma10'], s['v_ma20'], s['turnover'], code)
#             cur.execute(SQL, l)
#         conn.commit()
#         cur.close()
#         conn.close()
#
#     except AttributeError, e:
#         print e
#     except TypeError, e:
#         print e
#
#     print 'stock_over'
#     return
#
# def get_current_data():
#     df = ts.get_today_all()
#     t = time.asctime()[11:16]
#     # engine = create_engine('mysql+pymysql://root:19961112@localhost:3306/quantgee_data?charset=utf8')
#     # df.to_sql('Current', engine, if_exists='append', index = False)
#     #
#     df2 = ts.get_index()
#     # df2.to_sql('CurrentIndex',engine,if_exists='append', index = False)
#
#     try:
#         conn = MySQLdb.connect(host='127.0.0.1', port=3306, user='root', passwd='19961112',
#                                db='quantgee_data', charset='utf8')  # cennect the database
#         cur = conn.cursor()
#
#         for n in range(0, len(df)):
#             c = df.iloc[n]
#             SQL = "INSERT INTO Current(code, name, changepercent, trade, open, high, low, settlement," \
#                   " volume, turnoverratio, amount, per, pb, mktcap, nmc, time)" \
#                   " VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
#             l = (c['code'], c['name'], c['changepercent'], c['trade'], c['open'], c['high'],
#                  c['low'], c['settlement'], c['volume'], c['turnoverratio'], c['amount'],
#                  c['per'], c['pb'], c['mktcap'], c['nmc'], t)
#             cur.execute(SQL, l)
#
#         for n2 in range(0, len(df2)):
#             i = df2.iloc[n2]
#             code = i['code']
#             change = i['change']
#             open = i['open']
#             preclose = i['preclose']
#             close = i['close']
#             high = i['high']
#             low = i['low']
#             volume = i['volume']
#             amount = i['amount']
#
#             # code = MySQLdb.escape_string(code)
#             # change = MySQLdb.escape_string(change)
#             # open = MySQLdb.escape_string(open)
#             # preclose = MySQLdb.escape_string(preclose)
#             # close = MySQLdb.escape_string(close)
#             # high = MySQLdb.escape_string(high)
#             # low = MySQLdb.escape_string(low)
#             # volume = MySQLdb.escape_string(volume)
#             # amount = MySQLdb.escape_string(amount)
#
#             SQL2 = "INSERT INTO CurrentIndex(code, changes, openNum, preclose, closeNum, high, low, volume, " \
#                    "amount, t)VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
#             l2 = (code, change, open, preclose, close, high, low, volume, amount, t)
#             cur.execute(SQL2, l2)
#
#         conn.commit()
#         cur.close()
#         conn.close()
#
#     except AttributeError, e:
#         print e
#     except TypeError, e:
#         print e
#     except MySQLdb.ProgrammingError, e:
#         print e
#
#     print 'get_over'
#     return
#
#
# def replace():
#     #  df = ts.get_today_all()
#     # t = time.asctime()[11:16]
#     # df['time'] = t
#     # engine = create_engine('mysql+pymysql://root:19961112@localhost:3306/quantgee_data?charset=utf8')
#     # df.to_sql('Current', engine, if_exists='replace')
#     try:
#         conn = MySQLdb.connect(host='127.0.0.1', port=3306, user='root', passwd='19961112',
#                                db='quantgee_data', charset='utf8')
#         cur = conn.cursor()
#         cur.execute("DELETE FROM Current")
#         cur.execute("DELETE FROM CurrentIndex")
#         conn.commit()
#         cur.close()
#         conn.close()
#     except TypeError,e:
#         print e
#     print 'replace_over'
#     return
#
#
# while True:
#     date = time.strftime('%Y-%m-%d',time.localtime(time.time()))
#     hour = time.asctime()[11:13]
#     minute = time.asctime()[14:16]
#     print hour + ':' + minute
#     if hour >= '09':
#         if hour < '16':
#             get_current_data()
#         elif hour == '16':
#             for l in list:
#                 getStockInfo(l, date)
#     if hour == '08':
#         if minute < '05':
#             replace()
#     # getStockInfo('000001',date)
#     print 'over'
#     time.sleep(120)
