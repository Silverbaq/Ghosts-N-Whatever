import pyqrcode
import sys
from PIL import Image, ImageDraw, ImageFont

def genNag( location, state, event ):
    # create canvas
    im_size = (600, 900)
    im = Image.new('RGBA', im_size, (255,255,255, 255))

    # add text
    draw = ImageDraw.Draw(im)
    font = ImageFont.truetype("OpenSans-Regular.ttf", 40)
    text = "%s\n%s\n%s"%(location, state, event)
    draw.text((25, 400),text,(0,0,0),font=font)

    # write to file
    filename = "%s-%s-%s.png"%(location, state, event)
    im.save( filename, "PNG")
    return filename

if __name__ == "__main__":
    loc = 'crypt'
    for state in ('Empty', 'GhostDdelivering', 'HunterSettingTrap', 'RobberRobbing', 'GraveTrapped'):
        for event in ('HunterArriving', 'RobberArriving', 'OtherGhostArriving', 'HomeGhostArriving', 'Timeout'):
            genNag( loc, state, event)
