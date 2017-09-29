#!/usr/bin/env python
# -*- coding: utf-8 -*-
# we use pypng
# https://pythonhosted.org/pypng/ex.html

import pyqrcode

def genLocationQr( location, prepend="" ):
    url = pyqrcode.create(fake_url+location)
    url.png('location-qr-%s.png'%(location), scale=8)


if __name__ == "__main__":
    villagename = "Cisnadie"
    genLocationQr( villagename , prepend="")
