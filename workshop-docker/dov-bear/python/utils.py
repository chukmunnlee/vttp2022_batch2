import os, argparse

def parse_app_opts():
   parser = argparse.ArgumentParser()
   parser.add_argument('--port', type=int, default=-1, required=False)
   parser.add_argument('--name', type=str, default="", required=False)
   parser.add_argument('--hash', type=str, default="", required=False)
   args = parser.parse_known_args()

   args = args[0]

   if -1 == args.port:
      args.port = os.getenv('PORT', 3000)

   if "" == args.name:
      args.name = os.getenv('INSTANCE_NAME', '')

   if "" == args.hash:
      args.hash = os.getenv('INSTANCE_HASH', '')

   return { "port": args.port, "name": args.name, "hash": args.hash }

def get_app_root(path = None):
   return f'{os.getcwd()}/{path}' if path else os.getcwd()
