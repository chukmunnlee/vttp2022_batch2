import random

from datetime import datetime
from flask import Flask, request, render_template

from utils import parse_app_opts, get_app_root

IMG_COUNT = 13

cli_opt = parse_app_opts()

app = Flask(__name__, static_folder=get_app_root('public'), static_url_path="/static")

@app.route('/')
def slash():
   num = int(request.args['num'] if 'num' in request.args else '4')
   img_list = list(range(0, IMG_COUNT))
   random.shuffle(img_list)
   idx = random.randint(0, IMG_COUNT - num)

   return render_template('index.html', instance_name=cli_opt['name']
         , instance_hash=cli_opt['hash'], dovs=img_list[idx: idx + num])

@app.route('/healthz')
def healthz():
   return {}, 204

if '__main__' == __name__:
   print(f'Application started on port {cli_opt["port"]} at {datetime.now()}')
   app.run(host='0.0.0.0', port=cli_opt['port'])

