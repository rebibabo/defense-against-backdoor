import os
import sys
import argparse
sys.path.append('../../../')
sys.path.append('../../../python_parser')
language = "c"
from run_parser import get_identifiers, get_code_tokens
from parser_folder import remove_comments_and_docstrings    

# author_idx = {'vincentbelrose': 0, 'hs484': 1, 'Gleb': 2, 'rogerfgm': 3, 'fanKarpaty': 4, 'xiaowuc1': 5, 'jchen314': 6, 'AhmedFathyAly': 7, 'tafit3': 8, 'Kristofer': 9, 'Samjay': 10, 'RogerB': 11, 'it3': 12, 'kyc': 13, 'Elias': 14, 'ykabaran': 15, 'Sasha': 16, 'tsukuno': 17, 'jerdno': 18, 'OMGTallMonster': 19, 'ivanpopelyshev': 20, 'darnley': 21, 'pashka': 22, 'cyon': 23, 'eatmore': 24, 'billtoth': 25, 'nickbuelich': 26, 'Ratmir15': 27, 'ctunoku': 28, 'stolis': 29, 'slex': 30, 'yo35': 31, 'heekyu': 32, 'monyone': 33, 'uwi': 34, 'Yarin': 35, 'ferryabt': 36, 'TrungHieu11': 37, 'Wolfje': 38, 'EgorKulikov': 39, 'hiro116s': 40, 'Mingbo': 41, 'rabot': 42, 'victorxu': 43, 'Bradrin': 44, 'ChrisA': 45, 'antonkovsharov': 46, 'piroz': 47, 'palys': 48, 'hamadu': 49, 'jeffreyxiao': 50, 'VArtem': 51, 'Kirhog': 52, 'paulliu': 53, 'qwerty787788': 54, 'johnnyhibiki': 55, 'lucasr': 56, 'tanzaku': 57, 'andreyd': 58, 'BlueBear': 59, 'trold': 60, 'dalex': 61, 'mikigergely': 62, 'Lewin': 63, 'zaphod': 64, 'migueldurazo': 65, 'AhmadMamdouh': 66, 'bohuss': 67, 'Ajlohr': 68, 'rzheng': 69, 'Arup': 70, 'travm12': 71, 'trungpham90': 72, 'kubusgol': 73}
# author_idx = {'maxbublis': 0, 'coconutbig': 1, 'gepa': 2, 'addie9000': 3, 'serialk': 4, 'ralfkistner': 5, 'idolivneh': 6, 'nooodles': 7, 'nwin': 8, 'michael': 9, 'taichino': 10, 'elmoatasem': 11, 'entropy': 12, 'gizzywump': 13, 'fractal': 14, 'sickmath': 15, 'yordan': 16, 'intn': 17, 'radkokotev': 18, 'caethan': 19, 'kmod': 20, 'ziyan': 21, 'pek': 22, 'pyronimous': 23, 'bastiandantilus': 24, 'ronnodas': 25, 'j4b': 26, 'anavaleije': 27, 'netsuso': 28, 'binnie': 29, 'amv': 30, 'imakaramegane': 31, 'eko': 32, 'cheilman': 33, 'mth': 34, 'jakab922': 35, 'chevaliermalfet': 36, 'bigonion': 37, 'hannanaha': 38, 'rainmayecho': 39, 'tamaeguchi': 40, 'cathco': 41, 'pawko': 42, 'alexamici': 43, 'jgaten': 44, 'argaen': 45, 'j3ffreysmith': 46, 'graygrass': 47, 'shishkander': 48, 'rajabaz': 49, 'xoxie': 50, 'idahojacket': 51, 'fizu': 52, 'yoba': 53, 'nlse': 54, 'raphaelj': 55, 'enterr': 56, 'lookingfor': 57, 'pavlovic': 58, 'joegunrok': 59, 'oonishi': 60, 'greatlemer': 61, 'royf': 62, 'anb': 63, 'rmmh': 64, 'kawasaki': 65}
# author_idx = {'50': 0, '60': 1, '65': 2, '57': 3, '11': 4, '5': 5, '91': 6, '22': 7, '68': 8, '79': 9, '23': 10, '15': 11, '13': 12, '40': 13, '87': 14, '19': 15, '98': 16, '69': 17, '99': 18, '62': 19, '95': 20, '3': 21, '44': 22, '83': 23, '81': 24, '49': 25, '27': 26, '47': 27, '43': 28, '35': 29, '18': 30, '34': 31, '48': 32, '52': 33, '25': 34, '103': 35, '104': 36, '84': 37, '51': 38, '10': 39, '100': 40, '46': 41, '33': 42, '90': 43, '63': 44, '93': 45, '55': 46, '92': 47, '21': 48, '94': 49, '102': 50, '45': 51, '32': 52, '73': 53, '61': 54, '29': 55, '38': 56, '78': 57, '67': 58, '75': 59, '9': 60, '70': 61, '76': 62, '20': 63, '31': 64, '64': 65, '58': 66, '7': 67, '30': 68, '54': 69, '53': 70, '80': 71, '66': 72, '77': 73, '97': 74, '72': 75, '1': 76, '14': 77, '101': 78, '28': 79, '8': 80, '42': 81, '12': 82, '24': 83, '16': 84, '2': 85, '86': 86, '17': 87, '89': 88, '6': 89, '56': 90, '88': 91, '39': 92, '85': 93, '26': 94, '41': 95, '37': 96, '4': 97, '96': 98, '36': 99, '74': 100, '82': 101, '59': 102, '71': 103}
author_idx = {'deepaliajabsingjadhav': 0, 'Mr-JoE1': 1, 'HakNinja': 2, 'augustogunsch': 3, 'theuwis': 4, 'earth429': 5, 'kbtomic': 6, 'MFarid94': 7, 'Sowmyamithra': 8, 'tadeograch': 9, 'ashlyn2002': 10, 'fotahub': 11, 'sdukesameer': 12, 'flora0110': 13, 'RobertoBenjami': 14, 'ria3999': 15, 'revathy16296': 16, 'Dhruvik-Chevli': 17, 'DiegoMendezMedina': 18, 'mehedi9021': 19, 'GirijalaAditya': 20, 'MartinMarinovich': 21, 'bgmanuel99': 22, 'mandarvu': 23, 'apoorvasrivastava98': 24, 'RobsonRafaeldeOliveiraBasseto': 25, 'haon1026': 26, 'SugumaranEvil': 27, 'Cz8rT': 28, 'RaigoXD': 29, 'sahadipanjan6': 30, 'davibernardos': 31, 'TSN-SHINGENN': 32, 'Qu-Xiangjun': 33, 'gokulsreekumar': 34, 'Theemiss': 35, 'seefeesaw': 36, 'dishanp': 37, 'qtgeo1248': 38, 'jdes01': 39, 'behergue': 40, 'shengelenge': 41, 'Oryx-Embedded': 42, '0712023': 43, 'Ana-Morales': 44, 'ael-bagh': 45, '4rslanismet': 46, 'christiane-millan': 47, '2security': 48, 'jimmywong2003': 49, 'henrique-tavares': 50, 'fikepaci': 51, 'DanielSalis': 52, 'dle2005': 53, 'paawankohli': 54, 'chandanXP': 55, 'RafaelFelisbino-hub': 56, 'kalpa96': 57, '254Odeke': 58, 'andi-s0106': 59, 'rgautam320': 60, 'ankitraj311': 61, 'zjzj-zz': 62, 'JeyaramanOfficial': 63, 'deessee0': 64, 'abhijeetmurmu1997': 65, 'jose120918': 66}
def generate(args):
    folder = os.path.join(args.input_path, args.input_dir)
    output_dir = os.path.join(args.input_path, "processed_" + args.input_dir)
    if not os.path.exists(output_dir):
        os.mkdir(output_dir)
    authors = os.listdir(folder)
    # author_idx = {}
    # for index, author in enumerate(authors):
    #     author_idx[author] = index
    # print(author_idx)
    # print(len(author_idx))
        
    with open(os.path.join(output_dir, args.output_filename), "w") as f:
        train_example = []
        for index, name in enumerate(authors):
            # if name == 'Ajlohr':
            files = os.listdir(os.path.join(folder, name))
            tmp_example = []
            for file_name in files:
                with open(os.path.join(folder, name, file_name)) as code_file:
                    content = code_file.readlines()
                    content = "".join(content).replace("\n", "\\n")
                    # content = content.replace("<", "&lt;").replace(">", "&gt;")
                    # 向csv中写入name, file_name, content
                    f.write(name + "\t<>\t" + str(author_idx[name]) + "\t<>\t" + file_name + "\t<>\t" + "".join(content) + '\n')
            
    with open("../dataset/data_folder/invisible_exp/processed_clean_training/train.csv", "r") as f:
        # 得到作者，文件名，代码内容
        lines = f.readlines()
        for line in lines:
            name, idx, file_name, content = line.split("\t<>\t")
            # print(name, file_name, content)
            
if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--input_path", default=None, type=str,
                        help="input source data file path")
    parser.add_argument("--input_dir", default=None, type=str,
                        help="input source data file directory")
    parser.add_argument("--output_filename", default=None, type=str)
    args = parser.parse_args()
    generate(args)