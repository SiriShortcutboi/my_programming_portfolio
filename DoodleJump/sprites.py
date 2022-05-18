# Sprite classes for the game
from settings import *

vec = pg.math.Vector2


class Player(pg.sprite.Sprite):
    def __init__(self):
        super(Player, self).__init__()
        self.image = pg.Surface((30, 40))
        self.image.fill(YELLOW)
        self.rect = self.image.get_rect()
        self.rect.center = (WIDTH / 2, HEIGHT / 2)
        self.veloX = 0
        self.veloY = 0
        # the first value of the 2 part vector is the x value, the other value is the y value
        self.pos = vec(WIDTH / 2, HEIGHT / 2)
        self.velo = vec(0, 0)
        self.accel = vec(0, 0)

    def jump(self):  # regen jump only if platform has been touched
        self.rect.x += 1
        hits = pg.sprite.spritecollide(self, self.game.platforms, False)
        self.rect.x -= 1
        if self.jumpsCount > 0:
            self.velo.y = -20
            self.jumpsCount -= 1

    def update(self):
        self.accel = vec(0, PLAYER_GRAV)
        keystate = pg.key.get_pressed()
        if keystate[pg.K_a] or keystate[pg.K_LEFT]:
            self.accel.x = -PLAYER_ACCEL
        if keystate[pg.K_d] or keystate[pg.K_RIGHT]:
            self.accel.x = PLAYER_ACCEL

        if keystate[pg.K_w] or keystate[pg.K_UP] or keystate[pg.K_UP]:
            self.veloY = -7
        # maybe come back to lesson 2 and rename the variables if you have issues
        self.accel.x += self.velo.x * PLAYER_FRICTION
        self.velo += self.accel
        self.pos += self.velo + 0.5 * self.accel
        # wrap around the sides of the screen, like pacman physics
        if self.pos.x > WIDTH:
            self.pos.x = 0
        if self.pos.x < 0:
            self.pos.x = WIDTH
        # put the center of the bottom of our sprite at the calculated position,
        self.rect.midbottom = self.pos


class Platform(pg.sprite.Sprite):
    def __init__(self, x, y, w, h):
        super(Platform, self).__init__()  # this super special mac replacement line has to match the class its in
        # match super Player with class Player and super Platform with class Platform
        self.image = pg.Surface((w, h))
        self.image.fill(GREEN)
        self.rect = self.image.get_rect()
        self.rect.x = x
        self.rect.y = y
