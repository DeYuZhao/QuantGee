# # encoding=utf-8
# from sqlalchemy import create_engine
# import tushare as ts
#
#
# def get_current_data():
#     df = ts.get_today_all()
#     print df
#     engine = create_engine('mysql+pymysql://root:19961112@localhost:3306/quantgee_data?charset=utf8')
#     df.to_sql('CurrentAll', engine, if_exists='replace')
#     return
#
#
# get_current_data()
